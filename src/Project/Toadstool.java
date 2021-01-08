package Project;

import java.util.Arrays;
import java.util.List;

public class Toadstool extends Plant {
    Toadstool(World world, Position position) {
        super(world, position, new OrganismStats(0, 4,
                0, 12, 'T'));
    }

    @Override
    public Organism createChild(Position newPosition) {
        return new Toadstool(this.world, newPosition);
    }

    @Override
    public List<Action> defend(Organism attacker) {
        return Arrays.asList(
            new DieAction(attacker, DeathCause.TOADSTOOL),
            new DieAction(this, DeathCause.KILLED)
        );
    }
}
