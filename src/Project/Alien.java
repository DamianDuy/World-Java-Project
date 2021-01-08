package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alien extends Organism {
    private final int freezingRadius = 2;

    Alien(World world, Position position) {
        super(world, position, new OrganismStats(0, 0,
                500, 1, 'A'));
    }
    
    public int getFreezingRadius() {
        return this.freezingRadius;
    }

    @Override
    public Organism createChild(Position newPosition) {
        return new Alien(this.world, newPosition);
    }

    @Override
    public List<Action> move() {
        Random rand = new Random();
        List<Action> actions = new ArrayList<>();
        List<Position> positions = this.world.getFreeNeighborPositions(this);

        actions.add(new UnfreezeAction(this.position, this.freezingRadius));

        if (!positions.isEmpty()) {
            Position newPosition = positions.get(rand.nextInt(positions.size()));
            MoveAction moveAction = new MoveAction(this, newPosition);
            actions.add(moveAction);
            actions.add(new FreezeAction(newPosition, this.freezingRadius));
        } else {
            actions.add(new FreezeAction(this.position, this.freezingRadius));
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
