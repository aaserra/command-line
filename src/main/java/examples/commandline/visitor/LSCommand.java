package examples.commandline.visitor;

import examples.commandline.components.DirectoryComponent;
import examples.commandline.singletons.DirectoryManager;

public class LSCommand implements ICommand {

    @Override
    public String toString() {
        return "LS";
    }

    @Override
    public void exec(ICommandVisitor visitor) {
        visitor.visit(this);

        for(DirectoryComponent childDirectory : DirectoryManager.getInstance().getCurrentDirectory().getChilds()) {
            System.out.println(childDirectory.getName());
        }

        for(String file : DirectoryManager.getInstance().getCurrentDirectory().getFiles()) {
            System.out.println(file);
        }
    }
    
}

