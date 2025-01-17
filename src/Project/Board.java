package Project;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Board {
    private final int width;
    private final int height;
    private final Map<Position, Organism> map;
    private final char separator = ' ';

    Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new TreeMap<>(new PositionComparator());

        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                this.map.put(new Position(x, y), null);
            }
        }
    }

    public void addOrganism(Organism organism) {
        final Organism o = Objects.requireNonNull(organism, MessagesObjectNotNull.organismNull());
        final Position p = this.assertCorrectPosition(o.getPosition());
        final Organism destinationOrganism = this.map.get(p);

        if (destinationOrganism != null && destinationOrganism.isAlive()) {
            throw new IllegalArgumentException(
                String.format(
                    "Position %s is already occupied by another living organism %s",
                    p.toString(),
                    destinationOrganism.toString()
                )
            );
        }

        this.map.put(p, o);
    }

    public Organism getOrganism(Position position) {
        final Position p = this.assertCorrectPosition(position);

        return this.map.get(p);
    }

    public void removeOrganism(Organism organism) {
        final Organism o = Objects.requireNonNull(organism, MessagesObjectNotNull.organismNull());
        final Position p = this.assertCorrectPosition(o.getPosition());

        if (this.map.get(p) == null) {
            throw new IllegalArgumentException(
                String.format(
                    "%s has declared position %s but that position is empty on the board",
                    o.toString(),
                    p.toString()
                )
            );
        }

        this.map.put(p, null);
    }

    public List<Organism> getNeighborOrganisms(Position position, int radius) {
        return this.selectNeighbors(
                position,
                p -> this.map.containsKey(p) && this.map.get(p) != null,
                radius
            )
            .stream()
            .map(p -> this.map.get(p))
            .collect(Collectors.toList());
    }

    public List<Position> getNeighborPositions(Position position) {
        return this.selectNeighbors(position, p -> this.map.containsKey(p));
    }

    public List<Position> getNeighborFreePositions(Position position) {
        return this.selectNeighbors(
            position,
            p -> this.map.containsKey(p) && this.map.get(p) == null
        );
    }

    public List<Position> getAllFreePositions() {
        return this.map.entrySet()
            .stream()
            .filter(entry -> entry.getValue() == null)
            .map(entry -> entry.getKey())
            .collect(Collectors.toList());
    }

    public Position assertCorrectPosition(Position position) {
        final Position p = Objects.requireNonNull(position, MessagesObjectNotNull.positionNull());

        if (!this.map.containsKey(p)) {
            throw new IllegalArgumentException(
                String.format(
                    "Position %s does not exist on the %dx%d board",
                    p.toString(),
                    this.width,
                    this.height
                )
            );
        }

        return p;
    }
    
    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer((this.width + 1) * (this.height + 1));

        buffer.append(String.format("+%s+\n", "-".repeat(this.width)));

        for (Map.Entry<Position, Organism> entry : this.map.entrySet()) {
            Position p = entry.getKey();
            Organism o = entry.getValue();

            if (p.getX() == 0) {
                buffer.append('|');
            }

            if (o == null || o.isDead()) {
                buffer.append(this.separator);
            } else {
                buffer.append(o.getStats().getSign());
            }

            if (p.getX() == this.width - 1) {
                buffer.append('|');
                buffer.append('\n');
            }
        }

        buffer.append(String.format("+%s+\n", "-".repeat(this.width)));

        return buffer.toString();
    }

    private List<Position> selectNeighbors(Position position, Predicate<Position> predicate) {
        return this.selectNeighbors(position, predicate, 1);
    }

    private List<Position> selectNeighbors(Position position, Predicate<Position> predicate, int radius) {
        final Position p = this.assertCorrectPosition(position);
        final List<Position> positions = new ArrayList<>();

        for (int dy = -radius; dy <= radius; dy++) {
            for (int dx = -radius; dx <= radius; dx++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                final Position newPosition = new Position(p.getX() + dx, p.getY() + dy);

                if (predicate.test(newPosition)) {
                    positions.add(newPosition);
                }
            }
        }

        return positions;
    }
}
