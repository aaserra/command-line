package examples.commandline.visitor;

import examples.commandline.singletons.*;

public class PWDCommand implements ICommand {

    @Override
    public String toString() {
        return "PWD";
    }

    @Override
    public void exec(ICommandVisitor visitor) {
        visitor.visit(this);
        System.out.println(DirectoryManager.getInstance().getCurrentDirectory().getFullPath());
    }
    
}