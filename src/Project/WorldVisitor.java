package Project;

import java.util.List;
import java.util.logging.Logger;

public class WorldVisitor implements ActionVisitor {
    private static final Logger LOGGER = Logger.getLogger(Main.DEBUG_LOGGER);
    private final World world;

    WorldVisitor(World world) {
        this.world = world;
    }

    @Override
    public void visit(MoveAction action) {
        Organism o = action.getOrganism();

        LOGGER.info(
            String.format(
                "Organism %s moves from %s to %s",
                o.name(),
                o.getPosition().toString(),
                action.getDestination().toString()
            )
        );
        world.moveOrganism(o, action.getDestination());
    }

    @Override
    public void visit(DieAction action) {
        Organism o = action.getOrganism();

        LOGGER.info(
            String.format(
                "Organism %s dies due to a cause '%s'",
                o.name(),
                action.getDeathCause()
            )
        );
        world.removeOrganism(o);
    }

    @Override
    public void visit(DeliverAction action) {
        Organism o = action.getOrganism();

        LOGGER.info(
            String.format(
                "Organism %s is born at %s",
                o.name(),
                o.getPosition().toString()
            )
        );
        world.addOrganism(o);
    }

    @Override
    public void visit(FreezeAction action) {
        Position p = action.getCenter();
        int r = action.getRadius();

        LOGGER.info(
            String.format(
                "Freeze action was invoked at %s with radius %d",
                p.toString(),
                r
            )
        );
        world.freezeOrganisms(p, r);
    }

    @Override
    public void visit(UnfreezeAction action) {
        Position p = action.getCenter();
        int r = action.getRadius();

        LOGGER.info(
            String.format(
                "Unfreeze action was invoked at %s with radius %d",
                p.toString(),
                r
            )
        );
        world.unfreezeOrganisms(p, r);
    }
}
