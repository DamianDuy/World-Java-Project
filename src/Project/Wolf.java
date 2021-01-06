package Project;

public class Wolf extends Animal {
    Wolf(World world, Position position) {
        super(world, position);
    }

    @Override
    public void initialize() {
        this.power = 8;
        this.initiative = 5;
        this.lifespan = 20;
        this.powerToReproduce = 16;
        this.sign = 'W';
    }

    @Override
    public Organism reproduce(Position newPosition) {
        return new Wolf(this.world, newPosition);
    }
}
