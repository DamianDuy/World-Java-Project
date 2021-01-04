package Project;

import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrganismComparatorTest {
    @Test
    public void comparingByInitiativeTest(){
        World world = new World(6,6);
        Position position1 = new Position(1,1);
        Position position2 = new Position(2,2);
        Position position3 = new Position(3,3);
        Grass grass = new Grass(world, position1);
        Sheep sheep = new Sheep(world, position2);
        Wolf wolf = new Wolf(world, position3);
        TreeSet<Organism> organisms = new TreeSet<>(new OrganismComparator());
        organisms.add(grass);
        organisms.add(wolf);
        organisms.add(sheep);
        assertEquals(wolf, organisms.first());
    }
}
