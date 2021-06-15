package examples.commandline;

import examples.commandline.enums.CommandType;
import examples.commandline.visitor.CommandLinesBuilder;
import examples.commandline.visitor.CommandVisitor;

public class CommandLineApplication {

	public static void main(String[] args) {

		CommandLinesBuilder commandLinesBuilder = new CommandLinesBuilder();

		for(int i = 0; i < args.length ; i++) {
			if (isCommand(args[i])) {
				CommandType cmdType = CommandType.getValue(args[i]);

				if (cmdType != null) {
					String cmdParam = null;

					if (i < args.length - 1) cmdParam = isCommand(args[i + 1]) ? null : args[i + 1];

					commandLinesBuilder.addCommand(cmdType, cmdParam);
				}
			}
		}

		commandLinesBuilder.exec(new CommandVisitor());
	}

	/*
	public static void main(String[] args) {

		System.out.println("Command Line File Manager");
		System.out.println("	MKDIR [name] - Makes a directory. Ex: MKDIR abc");
		System.out.println("	TOUCH [name] - Creates a file. Ex: TOUCH file.txt");
		System.out.println("	CD [name]    - Go to directory. Ex1: cd abc, Ex2: ROOT\\abc");
		System.out.println("	PWD          - Shows the current directory.");
		System.out.println("	LS           - List of files and directories inside the current directory.");
		System.out.println("	QUIT         - Exit application.");

		CommandLinesBuilder commandLinesBuilder = new CommandLinesBuilder();
		boolean doExit = false;

		do {
			String[] commands = System.console().readLine("Write your commands: ").split("\\s");

			for(int i = 0; i < commands.length ; i++) {
				if (isCommand(commands[i])) {
					CommandType cmdType = CommandType.getValue(commands[i]);
					String cmdParam = null;

					if (cmdType != null) {
						if (cmdType == CommandType.QUIT) doExit = true;

						if (i < commands.length - 1) cmdParam = isCommand(commands[i + 1]) ? null : commands[i + 1];

						commandLinesBuilder.addCommand(cmdType, cmdParam);
					}
				}
			}

			commandLinesBuilder.exec(new CommandVisitor());

		} while (!doExit);

	}
	*/

	private static boolean isCommand(String text) {
		return text.matches("[A-Z]+");
	}

}
