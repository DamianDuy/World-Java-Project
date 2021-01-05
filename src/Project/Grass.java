package Project;

public class Grass extends Plant {
    Grass(World world, Position position) {
        super(world, position);
    }

    public void initialize() {
        this.power = 0;
        this.initiative = 0;
        this.lifespan = 6;
        this.powerToReproduce = 3;
        this.sign = 'G';
    }
    public Organism reproduce(Position newPosition){
        return new Grass(this.world, newPosition);
    }
}
