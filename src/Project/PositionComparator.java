package Project;

import java.util.Comparator;

public class PositionComparator implements Comparator<Position> {
    @Override
    public int compare(Position p1, Position p2) {
        if (p1.getY() < p2.getY()) {
            return -1;
        }

        if (p1.getY() > p2.getY()) {
            return 1;
        }

        if (p1.getX() < p2.getX()) {
            return -1;
        }

        if (p1.getX() > p2.getX()) {
            return 1;
        }

        return 0;
    }
}
