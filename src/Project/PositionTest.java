package Project;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @Test
    public void equalsTest() {
        Position position1 = new Position(4, 5);
        Position position2 = new Position(4, 5);
        assertEquals(true, position1.equals(position2));
    }
    @Test
    public void notEqualsTest() {
        Position position1 = new Position(4, 5);
        Position position2 = new Position(5, 5);
        assertEquals(false, position1.equals(position2));
    }
    @Test
    public void toStringTest() {
        Position position1 = new Position(4, 5);
        assertEquals("(4, 5)", position1.toString());
    }
}
