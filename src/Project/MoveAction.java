package Project;

public class MoveAction implements Action {
    private final Organism organism;
    private final Position destination;

    MoveAction(Organism organism, Position destination) {
        this.organism = organism;
        this.destination = destination;
    }

    public Organism getOrganism() {
        return this.organism;
    }

    public Position getDestination() {
        return this.destination;
    }

    @Override
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}
