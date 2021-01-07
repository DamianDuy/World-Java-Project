package Project;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {
    @Test
    public void moveToEmptyPositionTest() {
        World world = new World(5, 5);
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Sheep sheep = new Sheep(world, position1);
        world.addOrganism(sheep);
        world.moveOrganism(sheep, position2);
        assertEquals(position2,sheep.getPosition());
    }
    @Test
    public void moveToPositionWithWeakerTest() {
        World world = new World(5, 5);
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Sheep sheep = new Sheep(world, position1);
        Wolf wolf = new Wolf(world, position2);
        world.addOrganism(sheep);
        world.addOrganism(wolf);
        world.moveOrganism(wolf, position1);
        assertEquals(position1,wolf.getPosition());
    }
    @Test
    public void moveToOccupiedByTheSame() {
        World world = new World(5, 5);
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Wolf wolf1 = new Wolf(world, position1);
        Wolf wolf2 = new Wolf(world, position2);
        world.addOrganism(wolf1);
        world.addOrganism(wolf2);
        world.moveOrganism(wolf1, position2);
        assertEquals(position1,wolf1.getPosition());
    }
    @Test
    public void devourOrganism() {
        World world = new World(5, 5);
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Sheep sheep = new Sheep(world, position1);
        Wolf wolf = new Wolf(world, position2);
        world.addOrganism(sheep);
        world.addOrganism(wolf);
        world.moveOrganism(wolf, position1);
        assertFalse(sheep.isAlive());
    }
    @Test
    public void deathFromOldAge() {
        World world = new World(5, 5);
        Position position = new Position(1, 0);
        Grass grass = new Grass(world, position);
        world.addOrganism(grass);
        world.start();
        for (int i = 0; i < 6; i++){
            world.makeTurn();
        }
        assertFalse(grass.isAlive());
    }
    @Test
    public void attackerEatingToadstool() {
        World world = new World(5, 5);
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Toadstool toadstool = new Toadstool(world, position1);
        Wolf wolf = new Wolf(world, position2);
        world.addOrganism(toadstool);
        world.addOrganism(wolf);
        world.moveOrganism(wolf, position1);
        assertFalse(wolf.isAlive());
    }
}
