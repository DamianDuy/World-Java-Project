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

    Organism(World world, Position position) {
        this.world = world;
        this.position = position;

        this.initialize();
    }

    public abstract List<Action> move();

    public abstract List<Action> action();

    public List<Action> defend(Organism attacker) {
        if (!isStrongerThan(attacker)) {
            return Arrays.asList(new RemoveAction(this));
        }

        return Arrays.asList(new RemoveAction(attacker));
    }

    // public abstract Organism clone(Position newPosition);

    protected abstract void initialize();

    public int getInitiative() {
        return initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public void setPower(int newPower) {
        this.power = newPower;
    }

    public boolean canReproduce() {
        return power >= powerToReproduce;
    }

    public boolean isStrongerThan(Organism other) {
        return this.power > other.power;
    }

    // public ArrayList<Action> consequences(Organism attacker) {
    // Position position = new Position(-1, -1);
    // ArrayList<Action> actions = new ArrayList<>();

    // if (isStrongerThan(attacker)) {
    // actions.add(new Action(ActionType.REMOVE, position, attacker, 0));
    // } else {
    // actions.add(new Action(ActionType.REMOVE, position, this, 0));
    // }

    // return actions;
    // }

    @Override
    public String toString() {
        return String.format("Organism: power: " + power + " initiative: " + initiative + " lifespan: " + lifespan
                + " position: " + position);
    }
}
