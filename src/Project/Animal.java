package Project;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism {
    Animal(World world, Position position) {
        super(world, position);
    }

    @Override
    public List<Action> move() {
        Random rand = new Random();
        List<Position> positions = this.world.getFreeNeighborPositions(this.position);

        if (positions.isEmpty()) {
            Position position = positions.get(rand.nextInt(positions.size()));
        }
        return Collections.emptyList();
    }

    @Override
    public List<Action> action() {
        return Collections.emptyList();
    }
}
