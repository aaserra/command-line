package examples.commandline.visitor;

import org.springframework.util.StringUtils;

import examples.commandline.singletons.DirectoryManager;

public class MKDIRCommand implements ICommand {

    private final String param;

    public MKDIRCommand(String param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "MKDIR " + param;
    }

    @Override
    public void exec(ICommandVisitor visitor) {
        visitor.visit(this);
        
        if (!StringUtils.hasLength(param)) {
            System.out.println("Directory cannot be empty");
        } else {
            DirectoryManager.getInstance().getCurrentDirectory().addChild(param);
        }
    }
    
}
