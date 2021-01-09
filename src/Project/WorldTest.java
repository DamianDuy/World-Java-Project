package Project;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {
    @Test
    public void moveToEmptyPositionTest() {
        World world = new World(5, 5, 'c');
        Sheep sheep = new Sheep(world, new Position(1,0));
        world.addOrganism(sheep);
        world.moveOrganism(sheep, new Position(0,0));
        assertEquals(new Position(0,0),sheep.getPosition());
    }
    @Test
    public void moveToPositionWithWeakerTest() {
        World world = new World(5, 5, 'c');
        Position position = new Position(1, 0);
        Sheep sheep = new Sheep(world, position);
        Wolf wolf = new Wolf(world, new Position(0,0));
        world.addOrganism(sheep);
        world.addOrganism(wolf);
        world.moveOrganism(wolf, position);
        assertEquals(position,wolf.getPosition());
    }
    @Test
    public void moveToOccupiedByTheSameTest() {
        World world = new World(5, 5, 'c');
        Wolf wolf1 = new Wolf(world, new Position(1, 0));
        Wolf wolf2 = new Wolf(world, new Position(0, 0));
        world.addOrganism(wolf1);
        world.addOrganism(wolf2);
        world.moveOrganism(wolf1, new Position(0, 0));
        assertEquals(new Position(1,0),wolf1.getPosition());
    }
    @Test
    public void devourOrganismTest() {
        World world = new World(5, 5, 'c');
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
    public void deathFromOldAgeTest() {
        World world = new World(5, 5,'c');
        Position position = new Position(1, 0);
        Grass grass = new Grass(world, position);
        world.addOrganism(grass);
        world.switchOffRandomEvents();
        for (int i = 0; i < 8; i++){
            world.start();
            world.makeTurn();
        }
        assertFalse(grass.isAlive());
    }
    @Test
    public void attackerEatToadstoolTest() {
        World world = new World(5, 5,'c');
        Toadstool toadstool = new Toadstool(world, new Position(1,0));
        Wolf wolf = new Wolf(world, new Position(0,0));
        world.addOrganism(toadstool);
        world.addOrganism(wolf);
        world.moveOrganism(wolf, new Position(1,0));
        assertFalse(wolf.isAlive());
    }
    @Test
    public void newOrganismBirthTest() {
        World world = new World(1,2,'c');
        Grass grass = new Grass(world, new Position(0,0));
        world.addOrganism(grass);
        world.switchOffRandomEvents();
        world.start();
        for (int i = 0; i <= 3; i++) {
            world.makeTurn();
        }
        assertNull(world.getFreeRandomPosition());
    }
    @Test
    public void alienFreezeTest() {
        World world = new World(5, 5, 'c');
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Toadstool toadstool = new Toadstool(world, position1);
        Alien alien = new Alien(world, position2);
        world.addOrganism(toadstool);
        world.addOrganism(alien);
        world.start();
        world.makeTurn();
        assertTrue(toadstool.isFrozen());
    }
    @Test
    public void alienUnfreezeTest() {
        World world = new World(5, 5, 'c');
        Position position1 = new Position(1, 0);
        Position position2 = new Position(0, 0);
        Toadstool toadstool = new Toadstool(world, position1);
        Alien alien = new Alien(world, position2);
        world.addOrganism(toadstool);
        world.addOrganism(alien);
        world.start();
        world.makeTurn();
        WorldVisitor worldVisitor = new WorldVisitor(world);
        Action unfreezeAction = new UnfreezeAction(alien.getPosition(), alien.getFreezingRadius());
        unfreezeAction.accept(worldVisitor);
        assertFalse(toadstool.isFrozen());
    }
}
