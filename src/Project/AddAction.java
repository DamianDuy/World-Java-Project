package Project;

public class AddAction implements Action {
    private final Organism organism;

    AddAction(Organism organism) {
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
