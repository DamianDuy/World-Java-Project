package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Plant extends Organism {
    Plant(World world, Position position) {
        super(world, position);
    }

    @Override
    public List<Action> move() {
        return Collections.emptyList();
    }

    @Override
    public List<Action> action() {
        Random rand = new Random();
        List<Action> actions = new ArrayList<>();
        List<Position> positions = world.getNeighboringFreePositions(this);
        if (canReproduce() && !positions.isEmpty()) {
            Position positionRand = positions.get(rand.nextInt(positions.size()));
            Organism newOrganism = this.reproduce(positionRand);
            this.lowerPowerAfterReproduce();
            AddAction addAction = new AddAction(newOrganism);
            actions.add(addAction);
        }

        return actions;
    }

    @Override
    public void vitals(){
        this.lifespan--;
        this.power++;
    }
}
