package Project;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomEventManager {
    private static final List<Integer> ALIEN_CHANCES = Arrays.asList(15, 30, 45, 60, 80, 100);
    private int alienChanceIndex = 0;
    private final Alien alien;
    private final World world;

    RandomEventManager(World world) {
        this.world = world;
        this.alien = new Alien(world, null);

        this.alien.kill();
    }

    public List<Action> createEvents() {
        final List<Action> actions = new ArrayList<>();
        final List<Action> alienActions = this.createAlienEvents();

        if (alienActions != null) {
            // Has to check if the alienAction is null to avoid NullPointerException when the board is full.
            actions.addAll(alienActions);
        }

        return actions;
    }

    private List<Action> createAlienEvents() {
        final Position alienPosition = this.world.getFreeRandomPosition();

        if (alienPosition == null) {
            // There is no free position on the world so we just do nothing.
            return null;
        }

        final Random rand = new Random();
        final int r = rand.nextInt(100);

        if (r < ALIEN_CHANCES.get(this.alienChanceIndex)) {
            // Perform an alien event based on whether the alien is alive or not.
            this.alienChanceIndex = 0;

            if (this.alien.isDead()) {
                this.alien.setPosition(alienPosition);
                this.alien.revive();

                return Arrays.asList(new DeliverAction(this.alien));
            }

            return Arrays.asList(
                new DieAction(this.alien, DeathCause.DISAPPEARANCE),
                new UnfreezeAction(this.alien.getPosition(), this.alien.getFreezingRadius())
            );
        }

        // An event did not occur. Increase the chances of its occurrence for the
        // next turn according to the declared chances in a static member of
        // this class.
        //
        // This is for our own safety so that we won't accidentally set the
        // index to a greater number than the size of the chances list.
        // (If the last chance is equal to 100 this is not required, but
        // otherwise we would run into some nasty little exceptions.)
        this.alienChanceIndex = Math.min(
            this.alienChanceIndex + 1,
            ALIEN_CHANCES.size() - 1
        );

        return Collections.emptyList();
    }
}
