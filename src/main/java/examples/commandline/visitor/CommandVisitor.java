package examples.commandline.visitor;

public class CommandVisitor implements ICommandVisitor {

    @Override
    public void visit(CDCommand command) {
        System.out.println(command.toString());
    }

    @Override
    public void visit(MKDIRCommand command) {
        System.out.println(command.toString());
    }
    
    @Override
    public void visit(PWDCommand command) {
    }

    @Override
    public void visit(TOUCHCommand command) {
        System.out.println(command.toString());
    }

    @Override
    public void visit(LSCommand command) {
    }

    @Override
    public void visit(QUITCommand command) {
    }
    
}
