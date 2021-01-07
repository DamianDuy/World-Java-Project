package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alien extends Organism {
    Alien(World world, Position position) {
        super(world, position, new OrganismStats(0, 0, 0, 1, 'A'));
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

        if (!positions.isEmpty()) {
            Position position = positions.get(rand.nextInt(positions.size()));
            MoveAction moveAction = new MoveAction(this, position);
            actions.add(moveAction);
        }

        return actions;
    }

    @Override
    public List<Action> action() {
        return Collections.emptyList();
    }

    @Override
    public List<Action> vitalize(){
        return Collections.emptyList();
    }
}
