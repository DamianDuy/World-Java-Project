package Project;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public abstract class Organism {
    protected final OrganismStats stats;
    protected final World world;
    protected Position position;
    private boolean alive = true;
    private boolean isFrozen = false;

    Organism(World world, Position position, OrganismStats stats) {
        this.world = world;
        this.position = position;
        this.stats = stats;
    }

    public abstract Organism createChild(Position newPosition);

    public abstract List<Action> move();

    public abstract List<Action> action();

    public List<Action> vitalize() {
        List<Action> actions = new ArrayList<>();

        if (!this.frozen()) {
            this.stats.increaseAge();
            this.stats.boostPower(1);

            if (this.stats.getLifespan() <= 0) {
                actions.add(new DieAction(this, DeathCause.OLD_AGE));
            }
        }
        return actions;
    }

    public List<Action> defend(Organism attacker) {
        if (isStrongerThan(attacker)) {
            return Arrays.asList(new DieAction(attacker, DeathCause.KILLED));
        }

        return Arrays.asList(new DieAction(this, DeathCause.KILLED));
    }

    public OrganismStats getStats() {
        return this.stats;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean frozen() {
        return isFrozen;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public boolean isDead() {
        return !this.alive;
    }

    public String name() {
        return this.getClass().getSimpleName();
    }

    public void revive() {
        this.alive = true;
    }

    public void kill() {
        this.alive = false;
    }

    public void freeze() {
        this.isFrozen = true;
    }

    public void unfreeze() {
        this.isFrozen = false;
    }

    public boolean canReproduce() {
        return this.stats.getPower() >= this.stats.getPowerToReproduce();
    }

    public boolean isStrongerThan(Organism other) {
        return this.stats.getPower() > other.stats.getPower();
    }

    @Override
    public String toString() {
        return String.format(
            "Organism %s [position = %s, lifespan = %d]",
            this.name(),
            this.position.toString(),
            this.stats.getLifespan()
        );
    }
}
