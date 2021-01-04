package Project;

public interface ActionVisitor {
    public void visit(MoveAction action);

    public void visit(RemoveAction action);

    public void visit(AddAction action);

    public void visit(IncreasePowerAction action);
}
