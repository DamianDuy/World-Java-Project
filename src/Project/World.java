package Project;

import java.util.*;

public class World {
    private boolean started = false;
    private int turnCounter = 0;
    private final Board board;
    private final Set<Organism> organisms = new TreeSet<>(new OrganismComparator());
    private final List<Organism> newOrganisms;
    private final WorldVisitor visitor = new WorldVisitor(this);

    World(int width, int height) {
        this.board = new Board(width, height);
        this.newOrganisms = new ArrayList<>(width * height);
    }

    public void start() {
        this.started = true;

        this.organisms.addAll(this.newOrganisms);
    }

    public void makeTurn() {
        if (!this.started) {
            throw new RuntimeException("Cannot make turn without starting the world first.");
        }

        this.makeMoves();
        System.out.println("After moves:");
        this.print();
        this.makeActions();
        System.out.println("After actions:");
        this.print();
        this.vitalize();
        System.out.println("After lowering life span: ");
        this.print();
        this.purgeDead();
        System.out.println("=".repeat(64));
        this.organisms.addAll(this.newOrganisms);
        this.newOrganisms.clear();

        this.turnCounter++;
    }

    public void addOrganism(Organism organism) {
        this.board.addOrganism(organism);
        this.newOrganisms.add(organism);
    }

    public void moveOrganism(Organism organism, Position destination) {
        final Organism attacker = Objects.requireNonNull(organism);
        final Organism defender = this.board.getOrganism(destination);

        if (defender == null || defender.isDead()) {
            this.move(attacker, destination);
        } else if (!organism.getClass().equals(defender.getClass())) {
            final List<Action> actions = defender.defend(attacker);

            this.dispatchActions(actions);

            if (attacker.isAlive()) {
                this.move(attacker, destination);
            }
        }
    }

    public List<Position> getPossibleMovePositions(Organism organism) {
        final Organism o = Objects.requireNonNull(organism);

        return this.board.getNeighborPositions(o.getPosition());
    }

    public List<Position> getFreeNeighborPositions(Organism organism) {
        final Organism o = Objects.requireNonNull(organism);

        return this.board.getNeighborFreePositions(o.getPosition());
    }

    public void print() {
        this.board.print();
        System.out.println();
    }

    private void move(Organism organism, Position destination) {
        this.board.removeOrganism(organism);
        organism.setPosition(destination);
        this.board.addOrganism(organism);
    }

    private void makeMoves() {
        for (Organism o : this.organisms) {
            final List<Action> actions = o.move();

            this.dispatchActions(actions);
        }
    }

    private void vitalize() {
        for (Organism o : this.organisms) {
            o.vitals();
        }
    }

    private void makeActions() {
        for (Organism o : this.organisms) {
            final List<Action> actions = o.action();

            this.dispatchActions(actions);
        }
    }

    private void purgeDead() {
        for (Iterator<Organism> i = this.organisms.iterator(); i.hasNext();) {
            Organism o = i.next();

            if (!o.isAlive()) {
                this.board.removeOrganism(o);
                i.remove();
            }
        }
    }

    private void dispatchActions(List<Action> actions) {
        for (Action a : actions) {
            a.accept(this.visitor);
        }
    }
}
