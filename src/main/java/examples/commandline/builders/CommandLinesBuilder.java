package examples.commandline.builders;

import java.util.ArrayList;
import java.util.List;

import examples.commandline.enums.CommandType;
import examples.commandline.visitor.CDCommand;
import examples.commandline.visitor.CommandVisitor;
import examples.commandline.visitor.ICommand;
import examples.commandline.visitor.LSCommand;
import examples.commandline.visitor.MKDIRCommand;
import examples.commandline.visitor.PWDCommand;
import examples.commandline.visitor.QUITCommand;
import examples.commandline.visitor.TOUCHCommand;

public class CommandLinesBuilder {

    private CommandVisitor visitor;
    private List<ICommand> commands;

    private CommandLinesBuilder() {
        visitor = new CommandVisitor();
        commands = new ArrayList<>();
    }

    public static CommandLinesBuilder builder() {
        return new CommandLinesBuilder();
    }

    public CommandLinesBuilder addCommand(CommandType cmd, String param) {
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

        return this;
    }

    public void build() {
        for (ICommand cmd : commands) {
            cmd.exec(visitor);
        }
    }
    
}
