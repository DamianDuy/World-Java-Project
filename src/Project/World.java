package Project;

import java.util.*;
import java.util.logging.Logger;

public class World {
    private static final Logger LOGGER = Logger.getLogger(Main.DEBUG_LOGGER);
    private boolean started = false;
    private int turnCounter = 1;
    private final int width;
    private final int height;
    private final Board board;
    private final Set<Organism> organisms = new TreeSet<>(new OrganismComparator());
    private final List<Organism> newOrganisms;
    private final WorldVisitor visitor = new WorldVisitor(this);
    private final RandomEventManager randomEventManager = new RandomEventManager(this);

    World(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Board(this.width, this.height);
        this.newOrganisms = new ArrayList<>(this.width * this.height);
    }

    public void start() {
        this.started = true;

        this.organisms.addAll(this.newOrganisms);
        this.newOrganisms.clear();
        LOGGER.info(
            String.format(
                "Starting a new %dx%d world with initial position:\n%s",
                this.width,
                this.height,
                this.board.toString()
            )
        );
    }

    public void makeTurn() {
        if (!this.started) {
            throw new RuntimeException(
                "Cannot make turn without starting the world first"
            );
        }

        LOGGER.info(String.format("Turn %d begins", this.turnCounter));
        LOGGER.info("Organisms start their movements");
        this.makeMoves();
        LOGGER.info(
            String.format(
                "World after the movements:\n%s",
                this.board.toString()
            )
        );
        LOGGER.info("Organisms start their actions");
        this.makeActions();
        LOGGER.info(
            String.format("World after the actions:\n%s", this.board.toString())
        );
        LOGGER.info("Organisms start being vitalized");
        this.vitalizeOrganisms();
        LOGGER.info(
            String.format(
                "World after the vitalization:\n%s",
                this.board.toString()
            )
        );
        LOGGER.info("Organism corpses are being purged");
        this.purgeDead();
        LOGGER.info("Random events are happening now");
        this.makeRandomEvents();
        this.organisms.addAll(this.newOrganisms);
        this.newOrganisms.clear();
        LOGGER.info(String.format("Turn %d ends", this.turnCounter));

        this.turnCounter++;
    }

    public void addOrganism(Organism organism) {
        this.board.addOrganism(organism);
        this.newOrganisms.add(organism);
    }

    public void removeOrganism(Organism organism) {
        organism.kill();
        //this.board.removeOrganism(organism);
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

    public Position getFreeRandomPosition() {
        Random rand = new Random();
        List<Position> positions = this.board.getAllFreePositions();

        if (!positions.isEmpty()) {
            Position position = positions.get(rand.nextInt(positions.size()));
            return position;
        }

        return null;
    }

    public void freezeOrganisms(Position center, int radius) {
        this.board.getNeighborOrganisms(center, radius)
            .forEach(o -> o.freeze());
    }

    public void unfreezeOrganisms(Position center, int radius) {
        this.board.getNeighborOrganisms (center, radius)
            .forEach(o -> o.unfreeze());
    }

    @Override
    public String toString() {
        return String.format(
            "World %dx%d [organisms = %d, newborn organisms = %d]",
            this.width,
            this.height,
            this.organisms.size(),
            this.newOrganisms.size()
        );
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

    private void vitalizeOrganisms() {
        for (Organism o : this.organisms) {
            final List<Action> actions = o.vitalize();

            this.dispatchActions(actions);
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

            if (o.isDead()) {
                i.remove();
            }
        }
    }

    private void makeRandomEvents() {
        final List<Action> actions = this.randomEventManager.createEvents();

        this.dispatchActions(actions);
    }

    private void dispatchActions(List<Action> actions) {
        for (Action a : actions) {
            a.accept(this.visitor);
        }
    }
}
