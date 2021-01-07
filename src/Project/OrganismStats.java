package Project;

public class OrganismStats {
    private int power;
    private int lifespan;
    private final int powerToReproduce;
    private final int initiative;
    private final char sign;

    OrganismStats(
        int power,
        int powerToReproduce,
        int initiative,
        int lifespan,
        char sign
    ) {
        this.power = power;
        this.powerToReproduce = powerToReproduce;
        this.initiative = initiative;
        this.lifespan = lifespan;
        this.sign = sign;
    }

    public int getPower() {
        return this.power;
    }

    public int getPowerToReproduce() {
        return this.powerToReproduce;
    }

    public int getInitiative() {
        return this.initiative;
    }

    public int getLifespan() {
        return this.lifespan;
    }

    public char getSign() {
        return this.sign;
    }

    public void boostPower(int booster) {
        this.power += booster;
    }

    public void halfPower() {
        this.power /= 2;
    }

    public void increaseAge() {
        this.lifespan--;
    }
}
