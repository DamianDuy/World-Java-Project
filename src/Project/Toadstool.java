package Project;

import java.util.Arrays;
import java.util.List;

public class Toadstool extends Plant {
    Toadstool(World world, Position position) {
        super(world, position);
    }

    @Override
    public void initialize() {
        this.power = 0;
        this.initiative = 0;
        this.lifespan = 12;
        this.powerToReproduce = 4;
        this.sign = 'T';
    }

    @Override
    public Organism reproduce(Position newPosition) {
        return new Toadstool(this.world, newPosition);
    }

    @Override
    public List<Action> defend(Organism attacker) {
        return Arrays.asList(new RemoveAction(attacker), new RemoveAction(this));
    }
}
