package Project;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class OrganismComparatorTest {
    @Test
    public void comparingDifferentOrganismsTest() {
        World world = new World(6, 6);
        Grass grass = new Grass(world, new Position(1, 1));
        Sheep sheep = new Sheep(world, new Position(2, 2));
        Wolf wolf = new Wolf(world, new Position(3, 3));
        TreeSet<Organism> organisms = new TreeSet<>(new OrganismComparator());

        organisms.add(grass);
        organisms.add(wolf);
        organisms.add(sheep);

        Iterator<Organism> i = organisms.iterator();

        assertEquals(wolf, i.next());
        assertEquals(sheep, i.next());
        assertEquals(grass, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    public void comparingSameOrganismsTest() {
        World world = new World(6, 6);
        Sheep sheep1 = new Sheep(world, new Position(1, 1));
        Sheep sheep2 = new Sheep(world, new Position(2, 2));
        Wolf wolf = new Wolf(world, new Position(3, 3));
        TreeSet<Organism> organisms = new TreeSet<>(new OrganismComparator());

        organisms.add(sheep1);
        organisms.add(wolf);
        organisms.add(sheep2);

        Iterator<Organism> i = organisms.iterator();

        assertEquals(wolf, i.next());
        assertEquals(sheep1, i.next());
        assertEquals(sheep2, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    public void noSameOrganismsInSetTest() {
        World world = new World(6, 6);
        Grass grass = new Grass(world, new Position(1, 1));
        Sheep sheep = new Sheep(world, new Position(2, 2));
        Wolf wolf = new Wolf(world, new Position(3, 3));
        TreeSet<Organism> organisms = new TreeSet<>(new OrganismComparator());

        organisms.add(sheep);
        organisms.add(wolf);
        organisms.add(sheep);
        organisms.add(grass);
        organisms.add(wolf);

        Iterator<Organism> i = organisms.iterator();

        assertEquals(wolf, i.next());
        assertEquals(sheep, i.next());
        assertEquals(grass, i.next());
        assertFalse(i.hasNext());
    }
}
