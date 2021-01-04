package Project;

public interface Action {
    public void accept(ActionVisitor visitor);
}
