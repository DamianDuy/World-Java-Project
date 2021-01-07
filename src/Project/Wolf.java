package Project;

public class Wolf extends Animal {
    Wolf(World world, Position position) {
        super(world, position, new OrganismStats(8, 16, 5, 20, 'W'));
    }

    @Override
    public Organism createChild(Position newPosition) {
        return new Wolf(this.world, newPosition);
    }
}
