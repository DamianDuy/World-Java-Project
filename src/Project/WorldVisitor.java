package Project;

public class WorldVisitor implements ActionVisitor {
    private final World world;

    WorldVisitor(World world) {
        this.world = world;
    }

    @Override
    public void visit(MoveAction action) {
        world.move(action.getOrganism(), action.getDestination());
    }

    @Override
    public void visit(RemoveAction action) {
        action.getOrganism().kill();
    }

    @Override
    public void visit(AddAction action) {
        world.addOrganism(action.getOrganism());
    }

    @Override
    public void visit(IncreasePowerAction action) {
        action.getOrganism().setPower(action.getBoost());
    }
}
