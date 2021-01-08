package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alien extends Organism {
    Alien(World world, Position position) {
        super(world, position, new OrganismStats(0, 0,
                500, 1, 'A'));
    }
    
    @Override
    public Organism createChild(Position newPosition) {
        return new Alien(this.world, newPosition);
    }

    @Override
    public List<Action> move() {
        Random rand = new Random();
        List<Action> actions = new ArrayList<>();
        if (isAlive()) {
            List<Position> positions = this.world.getFreeNeighborPositions(this);
            actions.add(new UnfreezeAction(this.position, 2));

            if (!positions.isEmpty()) {
                Position newPosition = positions.get(rand.nextInt(positions.size()));
                MoveAction moveAction = new MoveAction(this, newPosition);
                actions.add(moveAction);
                actions.add(new FreezeAction(newPosition, 2));
            } else {
                actions.add(new FreezeAction(this.position, 2));
            }
        }
        return actions;
    }

    @Override
    public List<Action> action() {
        return Collections.emptyList();
    }

    @Override
    public List<Action> vitalize() {
        return Collections.emptyList();
    }
}
