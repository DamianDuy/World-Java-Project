package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism {
    Plant(World world, Position position, OrganismStats stats) {
        super(world, position, stats);
    }

    @Override
    public List<Action> move() {
        return Collections.emptyList();
    }

    @Override
    public List<Action> action() {
        Random rand = new Random();
        List<Action> actions = new ArrayList<>();

        if (canReproduce()) {
            List<Position> positions = this.world.getFreeNeighborPositions(this);

            if (!positions.isEmpty()) {
                Position position = positions.get(rand.nextInt(positions.size()));
                Organism newOrganism = this.createChild(position);
                DeliverAction addAction = new DeliverAction(newOrganism);

                this.stats.halfPower();
                actions.add(addAction);
            }
        }

        return actions;
    }
}
