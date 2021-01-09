package Project;

import java.util.logging.Logger;

public class WorldVisitor implements ActionVisitor {
    private final World world;

    WorldVisitor(World world) {
        this.world = world;
    }

    @Override
    public void visit(MoveAction action) {
        Organism o = action.getOrganism();
        world.moveOrganism(o, action.getDestination());
    }

    @Override
    public void visit(DieAction action) {
        Organism o = action.getOrganism();
        world.removeOrganism(o, action.getDeathCause());
    }

    @Override
    public void visit(DeliverAction action) {
        Organism o = action.getOrganism();
        world.addOrganism(o);
    }

    @Override
    public void visit(FreezeAction action) {
        Position p = action.getCenter();
        int r = action.getRadius();
        world.freezeOrganisms(p, r);
    }

    @Override
    public void visit(UnfreezeAction action) {
        Position p = action.getCenter();
        int r = action.getRadius();
        world.unfreezeOrganisms(p, r);
    }
}
