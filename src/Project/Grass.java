package Project;

public class Grass extends Plant {
    Grass(World world, Position position) {
        super(world, position, new OrganismStats(0, 3,
                0, 6, 'G'));
    }

    @Override
    public Organism createChild(Position newPosition) {
        return new Grass(this.world, newPosition);
    }
}
