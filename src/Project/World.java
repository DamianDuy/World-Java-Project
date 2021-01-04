package Project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class World {
    private final int width;
    private final int height;
    private int turnCounter = 0;
    private final char separator = '.';
    private final Map<Position, Organism> map;
    private final Set<Organism> organisms = new TreeSet<>(new OrganismComparator());
    private final WorldVisitor visitor = new WorldVisitor(this);

    World(int width, int height) {
        this.width = width;
        this.height = height;
        this.map = new HashMap<>();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.map.put(new Position(x, y), null);
            }
        }
    }

    public void makeTurn() {
        for (Organism o : this.organisms) {
            List<Action> actions = o.move();

            for (Action a : actions) {
                a.accept(this.visitor);
            }
        }

        this.turnCounter++;
    }

    public void move(Organism organism, Position destination) {
        if (this.isCorrectPosition(destination)) {
            this.map.put(organism.getPosition(), null);
            this.map.put(destination, organism);
            organism.setPosition(destination);
        }
    }

    public void addOrganism(Organism organism) {
        Position position = organism.getPosition();

        if (this.isCorrectPosition(position)) {
            this.map.put(position, organism);
            this.organisms.add(organism);
        }
    }

    public List<Position> getFreeNeighborPositions(Position position) {
        ArrayList<Position> positions = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                Position p = new Position(position.getX() + dx, position.getY() + dy);

                if (this.isCorrectPosition(p)) {
                    positions.add(p);
                }
            }
        }

        return positions;
    }

    private boolean isCorrectPosition(Position position) {
        return this.map.containsKey(position);
    }
}
