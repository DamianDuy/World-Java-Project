package Project;

import java.util.ArrayList;

public abstract class Organism{
    protected int power;
    protected int initiative;
    protected Position position;
    protected int liveLength;
    protected int powerToReproduce;
    protected char sign;
    protected World world;
    Organism(Position position, World world){
        this.position = position;
        this.world = world;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void move();
    public abstract ArrayList<Action> action();
    public abstract void initParams();
    public abstract Organism clone(Position newPosition);

    public boolean ifReproduce(){
        if (power >= powerToReproduce){
            return true;
        }
        return false;
    }
    public ArrayList<Action> consequences(Organism attackingOrganism){
        Position position = new Position(-1, -1);
        ArrayList<Action> actions = new ArrayList<>();
        if (this.power > attackingOrganism.power){
            actions.add(new Action(ActionEnum.A_REMOVE, position, 0, attackingOrganism));
        }
        else{
            actions.add(new Action(ActionEnum.A_REMOVE, position, 0, this));
        }
        return actions;
    }

    @Override
    public String toString(){
        return String.format("Organism: power: " + power + " initiative: " + initiative + " live length: "
                + liveLength + " position: " + position);
    }

}
