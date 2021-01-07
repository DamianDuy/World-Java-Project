package Project;

public class DieAction implements Action {
    private final Organism organism;
    private final DeathCause deathCause;

    DieAction(Organism organism, DeathCause deathCause) {
        this.organism = organism;
        this.deathCause = deathCause;
    }

    public Organism getOrganism() {
        return this.organism;
    }

    public DeathCause getDeathCause() {
        return this.deathCause;
    }

    @Override
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}
