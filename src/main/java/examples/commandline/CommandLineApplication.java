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

	private static boolean isCommand(String text) {
		return text.matches("[A-Z]+");
	}

}
