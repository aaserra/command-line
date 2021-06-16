package examples.commandline;

import examples.commandline.builders.CommandLinesBuilder;
import examples.commandline.enums.CommandType;

public class CommandLineApplication {

	public static void main(String[] args) {

		CommandLinesBuilder commandLinesBuilder = CommandLinesBuilder.builder();

		if (args != null) {
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
		}
		
		commandLinesBuilder.build();
	}

	private static boolean isCommand(String text) {
		return text.matches("[A-Z]+");
	}

}
