package Project;

public class Sheep extends Animal {
    Sheep(World world, Position position) {
        super(world, position, new OrganismStats(3, 6,
                3, 10, 'S'));
    }

    @Override
    public Organism createChild(Position newPosition) {
        return new Sheep(this.world, newPosition);
    }
}
