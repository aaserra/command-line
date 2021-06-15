package examples.commandline.enums;

public enum CommandType {
    MKDIR,
    CD,
    PWD,
    TOUCH,
    LS,
    QUIT;

    public static CommandType getValue(String command) {
        try {
            return CommandType.valueOf(command);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command name");
            return null;
        }
    }
}
