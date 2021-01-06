package Project;

import java.util.Arrays;
import java.util.List;

public abstract class Organism {
    protected int power;
    protected int powerToReproduce;
    protected int initiative;
    protected int lifespan;
    protected char sign;
    protected World world;
    protected Position position;
    private boolean alive = true;
    private boolean isFrozen = false;

    Organism(World world, Position position) {
        this.world = world;
        this.position = position;

        this.initialize();
    }

    public abstract List<Action> move();

    public abstract List<Action> action();

    public List<Action> defend(Organism attacker) {
        if (isStrongerThan(attacker)) {
            return Arrays.asList(new RemoveAction(attacker));
        }

        return Arrays.asList(new RemoveAction(this));
    }

    public abstract Organism reproduce(Position newPosition);

    protected abstract void initialize();

    public abstract void vitals();

    public int getInitiative() {
        return initiative;
    }

    public Position getPosition() {
        return position;
    }

    public char getSign() { return sign; }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public void kill() {
        alive = false;
    }

    public void setPower(int newPower) {
        this.power = newPower;
    }

    public void freeze() {
        isFrozen = true;
    }

    public boolean canReproduce() {
        return power >= powerToReproduce;
    }

    public boolean isStrongerThan(Organism other) {
        return this.power > other.power;
    }

    public void lowerPowerAfterReproduce() {
        this.power /= 2;
    }

    @Override
    public String toString() {
        return String.format(
            "Organism %s [position = %s, lifespan = %d]",
            this.getClass().getSimpleName(),
            this.position.toString(),
            this.lifespan
        );
    }
}
