package Project;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Position)) {
            return false;
        }

        Position otherPosition = (Position) other;

        return otherPosition.x == this.x && otherPosition.y == this.y;
    }

    @Override
    public String toString() {
        return String.format("(" + this.x + ", " + this.y + ")");
    }
}
