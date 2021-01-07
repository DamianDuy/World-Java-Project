package Project;

import org.junit.Test;

import java.util.Iterator;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PositionComparatorTest {
    @Test
    public void comparePositionsTest(){
        TreeSet<Position> positions = new TreeSet<>(new PositionComparator());
        Position position1 = new Position(0,3);
        Position position2 = new Position(1,1);
        Position position3 = new Position(4,1);
        Position position4 = new Position(0,0);

        positions.add(position1);
        positions.add(position2);
        positions.add(position3);
        positions.add(position4);

        Iterator<Position> i = positions.iterator();

        assertEquals(position4, i.next());
        assertEquals(position2, i.next());
        assertEquals(position3, i.next());
        assertEquals(position1, i.next());
        assertFalse(i.hasNext());
    }
}
