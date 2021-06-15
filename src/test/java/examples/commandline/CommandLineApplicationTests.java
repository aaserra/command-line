package examples.commandline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import examples.commandline.singletons.DirectoryManager;

class CommandLineApplicationTests {

	private final ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final ByteArrayOutputStream err = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setStreams() {
		System.setOut(new PrintStream(out));
		System.setErr(new PrintStream(err));
		DirectoryManager.createNewInstance();
	}

	@AfterEach
	public void restoreInitialStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@ParameterizedTest(name = "{index}: {0}")
	@MethodSource("getParameterizedTestCases")
	void contextLoads(String arguments, String expected) {
		// Arrange
		String[] args = arguments.split("\\s");

		// ACT
		CommandLineApplication.main(args);

		// Assert
		assertEquals(expected, out.toString());
	}

	static Stream<Arguments> getParameterizedTestCases() {
		return Stream.of(
			Arguments.arguments("QUIT", "exit\r\n"),
			Arguments.arguments("CD \\ PWD QUIT", "CD \\\r\nROOT\r\nexit\r\n"),
			Arguments.arguments("MKDIR", "MKDIR null\r\nDirectory cannot be empty\r\n"),
			Arguments.arguments("MKDIR s3d6\\ahd", "MKDIR s3d6\\ahd\r\nInvalid directory name\r\n"),
			Arguments.arguments("MKDIR abc CD", "MKDIR abc\r\nCD null\r\nDirectory cannot be empty\r\n"),
			Arguments.arguments("MKDIR abc MKDIR abc", "MKDIR abc\r\nMKDIR abc\r\nDirectory already exists\r\n"),
			Arguments.arguments("MKDIR abc CD abc PWD QUIT", "MKDIR abc\r\nCD abc\r\nROOT\\abc\r\nexit\r\n"),
			Arguments.arguments("MKDIR abc CD cba PWD QUIT", "MKDIR abc\r\nCD cba\r\nDirectory not found\r\nROOT\r\nexit\r\n"),
			Arguments.arguments("MKDIR abc CD abc MKDIR cde CD cde PWD QUIT", "MKDIR abc\r\nCD abc\r\nMKDIR cde\r\nCD cde\r\nROOT\\abc\\cde\r\nexit\r\n"),
			Arguments.arguments("MKDIR abc CD abc MKDIR dir1 MKDIR dir2 MKDIR dir3 LS", "MKDIR abc\r\nCD abc\r\nMKDIR dir1\r\nMKDIR dir2\r\nMKDIR dir3\r\ndir1\r\ndir2\r\ndir3\r\n"),
			Arguments.arguments("MKDIR abc CD abc MKDIR dir1 MKDIR dir2 TOUCH", "MKDIR abc\r\nCD abc\r\nMKDIR dir1\r\nMKDIR dir2\r\nTOUCH null\r\nFilename cannot be empty\r\n"),
			Arguments.arguments("MKDIR abc CD abc MKDIR dir1 MKDIR dir2 TOUCH filename", "MKDIR abc\r\nCD abc\r\nMKDIR dir1\r\nMKDIR dir2\r\nTOUCH filename\r\nInvalid file name\r\n"),
			Arguments.arguments("MKDIR abc CD abc MKDIR dir1 MKDIR dir2 TOUCH filename1.txt TOUCH filename2.txt LS", "MKDIR abc\r\nCD abc\r\nMKDIR dir1\r\nMKDIR dir2\r\nTOUCH filename1.txt\r\nTOUCH filename2.txt\r\ndir1\r\ndir2\r\nfilename1.txt\r\nfilename2.txt\r\n"),
			Arguments.arguments("MKDIR abc CD abc MKDIR dir1 MKDIR dir2 MKDIR dir3 CD \\ CD \\root\\abc\\dir3 PWD QUIT", "MKDIR abc\r\nCD abc\r\nMKDIR dir1\r\nMKDIR dir2\r\nMKDIR dir3\r\nCD \\\r\nCD \\root\\abc\\dir3\r\nROOT\\abc\\dir3\r\nexit\r\n")
		);
	}

}
