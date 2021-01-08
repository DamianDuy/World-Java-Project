package Project;

public class Dandelion extends Plant {
    Dandelion(World world, Position position) {
        super(world, position, new OrganismStats(0, 2,
                0, 6, 'D'));
    }

    @Override
    public Organism createChild(Position newPosition) {
        return new Dandelion(this.world, newPosition);
    }
}
