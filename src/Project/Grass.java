package Project;

public class Grass extends Plant{
    Grass(Position position, World world){
        super(position, world);
        initParams();
    }
    public Organism clone(Position newPosition){
        Grass childGrass = new Grass (newPosition, world);
        return this;
    }
    public void initParams(){
        this.power = 0;
        this.initiative = 0;
        this.liveLength = 6;
        this.powerToReproduce = 3;
        this.sign = 'G';
    }

}
