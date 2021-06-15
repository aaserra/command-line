package examples.commandline.visitor;

public interface ICommandVisitor {
    void visit(CDCommand command);
    void visit(MKDIRCommand command);
    void visit(PWDCommand command);
    void visit(TOUCHCommand command);
    void visit(LSCommand command);
    void visit(QUITCommand command);
}
