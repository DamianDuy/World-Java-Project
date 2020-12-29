package Project;

public abstract class Organism{
    private int power;
    private int initiative;
    private Position position;
    private int powerToReproduce;
    private char sign;
    private World world;
    public abstract void move();
    public abstract void action();
    public abstract void initParams();
    public abstract Organism clone();

}
