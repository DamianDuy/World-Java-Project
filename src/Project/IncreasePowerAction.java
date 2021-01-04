package Project;

public class IncreasePowerAction implements Action {
    private final int boost;
    private final Organism organism;

    IncreasePowerAction(Organism organism, int boost) {
        this.organism = organism;
        this.boost = boost;
    }

    public Organism getOrganism() {
        return this.organism;
    }

    public int getBoost() {
        return this.boost;
    }

    @Override
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}
