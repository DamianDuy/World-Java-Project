package Project;

public class Dandelion extends Plant {
    Dandelion(World world, Position position) {
        super(world, position);
    }

    @Override
    public void initialize() {
        this.power = 0;
        this.initiative = 0;
        this.lifespan = 6;
        this.powerToReproduce = 2;
        this.sign = 'D';
    }

    @Override
    public Organism reproduce(Position newPosition) {
        return new Dandelion(this.world, newPosition);
    }
}
