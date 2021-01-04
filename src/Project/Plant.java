package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        List<Action> actions = new ArrayList<>();

        if (canReproduce()) {

        }

        return actions;
    }
}
