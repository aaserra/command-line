package examples.commandline.visitor;

import java.util.ArrayList;
import java.util.List;

import examples.commandline.enums.CommandType;

public class CommandLinesBuilder implements ICommand {

    private List<ICommand> commands = new ArrayList<>();

    public void addCommand(CommandType cmd, String param) {
        switch(cmd) {
            case MKDIR:
                commands.add(new MKDIRCommand(param));
                break;
            case CD:
                commands.add(new CDCommand(param));
                break;
            case PWD:
                commands.add(new PWDCommand());
                break;
            case TOUCH:
                commands.add(new TOUCHCommand(param));
                break;
            case LS:
                commands.add(new LSCommand());
                break;
            case QUIT:
                commands.add(new QUITCommand());
                break;
        }
    }

    private void clearCommands() {
        commands = new ArrayList<>();
    }

    @Override
    public void exec(ICommandVisitor visitor) {
        for (ICommand cmd : commands) {
            cmd.exec(visitor);
        }

        clearCommands();
    }
    
}
