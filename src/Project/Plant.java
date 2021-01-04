package Project;

import java.util.ArrayList;

public abstract class Plant extends Organism{
    Plant(Position position, World world){
        super(position, world);
    }
    public void move(){}
    public ArrayList<Action> action(){}
    public abstract void initParams();
    public abstract Organism clone(Position newPosition);
}
