package Project;

public interface ActionVisitor {
    public void visit(MoveAction action);

    public void visit(DieAction action);

    public void visit(DeliverAction action);

    public void visit(FreezeAction action);

    public void visit(UnfreezeAction action);
}
