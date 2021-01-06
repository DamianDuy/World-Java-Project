package Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Animal extends Organism {
    Animal(World world, Position position) {
        super(world, position);
    }

    @Override
    public List<Action> move() {
        Random rand = new Random();
        List<Action> actions = new ArrayList<>();

        if (isAlive()) {
            List<Position> positions = this.world.getPossibleMovePositions(this);

            if (!positions.isEmpty()) {
                Position position = positions.get(rand.nextInt(positions.size()));
                MoveAction moveAction = new MoveAction(this, position);

                actions.add(moveAction);
            }
        }

        return actions;
    }

    @Override
    public List<Action> action() {
        Random rand = new Random();
        List<Action> actions = new ArrayList<>();

        if (isAlive() && canReproduce()) {
            List<Position> positions = this.world.getFreeNeighborPositions(this);

            if (!positions.isEmpty()) {
                Position position = positions.get(rand.nextInt(positions.size()));
                Organism newOrganism = this.reproduce(position);
                AddAction addAction = new AddAction(newOrganism);

                this.lowerPowerAfterReproduce();
                actions.add(addAction);
            }
        }

        return actions;
    }

    @Override
    public void vitals() {
        this.lifespan--;
        this.power++;

        if (this.lifespan <= 0) {
            this.kill();
        }
    }
}
