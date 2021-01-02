package Project;

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
    public abstract void action();
    public abstract void initParams();
    public abstract Organism clone(Position newPosition);

    public boolean ifReproduce(){
        if (power >= powerToReproduce){
            return true;
        }
        return false;
    }

    public Action consequences(Organism attackingOrganism){
        Position position = new Position(-1, -1);
        Action action;
        if (this.power > attackingOrganism.power){
            action = new Action(ActionEnum.A_REMOVE, position, 0, attackingOrganism);
        }
        else{
            action = new Action(ActionEnum.A_REMOVE, position, 0, this);
        }
        return action;
    }

    @Override
    public String toString(){
        return String.format("Organism: power: " + power + " initiative: " + initiative + " live length: "
                + liveLength + " position: " + position);
    }

}
