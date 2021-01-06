package Project;

public class Sheep extends Animal {
    Sheep(World world, Position position) {
        super(world, position);
    }

    @Override
    public void initialize() {
        this.power = 3;
        this.initiative = 3;
        this.lifespan = 10;
        this.powerToReproduce = 6;
        this.sign = 'S';
    }

    @Override
    public Organism reproduce(Position newPosition) {
        return new Sheep(this.world, newPosition);
    }
}
