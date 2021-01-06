package Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Alien extends Organism {

    Alien(World world, Position position){
        super(world, position);
    }
    
    @Override
    public Organism reproduce(Position newPosition){
        return new Alien(this.world, newPosition);
    }

    @Override
    protected void initialize(){
        this.power = 0;
        this.initiative = 0;
        this.lifespan = 1;
        this.powerToReproduce = 0;
        this.sign = 'A';
    }

    @Override
    public void vitals(){}

    @Override
    public List<Action> move(){
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
    public List<Action> action(){
        return Collections.emptyList();
    }
}
