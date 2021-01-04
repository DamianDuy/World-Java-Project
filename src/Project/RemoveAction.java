package Project;

public class RemoveAction implements Action {
    private final Organism organism;

    RemoveAction(Organism organism) {
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
