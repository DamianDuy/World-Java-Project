package Project;

public class FreezeAction implements Action {
    private final int radius;
    private final Position center;

    FreezeAction(Position center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public Position getCenter() {
        return this.center;
    }

    @Override
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}
