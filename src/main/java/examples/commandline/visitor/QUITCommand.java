package examples.commandline.visitor;

public class QUITCommand implements ICommand {

    @Override
    public String toString() {
        return "QUIT";
    }

    @Override
    public void exec(ICommandVisitor visitor) {
        visitor.visit(this);

        System.out.println("exit");
    }
    
}