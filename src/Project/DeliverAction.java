package Project;

public class DeliverAction implements Action {
    private final Organism organism;

    DeliverAction(Organism organism) {
        this.organism = organism;
    }

    public Organism getOrganism() {
        return this.organism;
    }

    @Override
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}
