package examples.commandline.visitor;

import org.springframework.util.StringUtils;

import examples.commandline.singletons.DirectoryManager;

public class TOUCHCommand implements ICommand {

    private final String param;

    public TOUCHCommand(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "TOUCH " + param;
    }

    @Override
    public void exec(ICommandVisitor visitor) {
        visitor.visit(this);

        if (!StringUtils.hasLength(param)) {
            System.out.println("Filename cannot be empty");
        } else {
            DirectoryManager.getInstance().getCurrentDirectory().addFile(param);
        }
    }
    
}

