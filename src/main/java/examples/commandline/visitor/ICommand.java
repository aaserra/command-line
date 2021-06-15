package examples.commandline.visitor;

public interface ICommand {
    void exec(ICommandVisitor visitor);

    String toString();
}
