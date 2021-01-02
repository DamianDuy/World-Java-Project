package Project;

public abstract class Plant extends Organism{
    Plant(Position position, World world){
        super(position, world);
    }
    public void move(){}
    public void action(){}
    public abstract void initParams();
    public abstract Organism clone(Position newPosition);
}
