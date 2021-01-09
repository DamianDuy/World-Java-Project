package Project;

import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;

public class World {
    private static final Logger LOGGER = Logger.getLogger(Main.DEBUG_LOGGER);
    private boolean started = false;
    private boolean areRandomEvents = true;
    private int turnCounter = 1;
    private final int width;
    private final int height;
    private final Board board;
    private final Set<Organism> organisms = new TreeSet<>(new OrganismComparator());
    private final List<Organism> newOrganisms;
    private final WorldVisitor visitor = new WorldVisitor(this);
    private final RandomEventManager randomEventManager = new RandomEventManager(this);
    private WorldWriter worldWriter;

    World(int width, int height, char whichWriter) {
        this.width = width;
        this.height = height;
        this.board = new Board(this.width, this.height);
        this.newOrganisms = new ArrayList<>(this.width * this.height);
        setWorldWriter(whichWriter);
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
        worldWriter.print(String.format(
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
        worldWriter.print(String.format("Turn %d begins", this.turnCounter));
        LOGGER.info("Organisms start their movements");
        worldWriter.print("Organisms start their movements");
        this.makeMoves();
        LOGGER.info(
            String.format(
                "World after the movements:\n%s",
                this.board.toString()
            )
        );
        worldWriter.print(String.format(
                "World after the movements:\n%s",
                this.board.toString()
        ));
        LOGGER.info("Organisms start their actions");
        worldWriter.print("Organisms start their actions");
        this.makeActions();
        LOGGER.info(
            String.format("World after the actions:\n%s", this.board.toString())
        );
        worldWriter.print(
                String.format("World after the actions:\n%s", this.board.toString())
        );
        LOGGER.info("Organisms start being vitalized");
        worldWriter.print("Organisms start being vitalized");
        this.vitalizeOrganisms();
        LOGGER.info(
            String.format(
                "World after the vitalization:\n%s",
                this.board.toString()
            )
        );
        worldWriter.print(String.format(
                "World after the vitalization:\n%s",
                this.board.toString()
        ));
        LOGGER.info("Organism corpses are being purged");
        worldWriter.print("Organism corpses are being purged");
        this.purgeDead();
        if (areRandomEvents) {
            LOGGER.info("Random events are happening now");
            worldWriter.print("Random events are happening now");
            this.makeRandomEvents();
        }
        this.organisms.addAll(this.newOrganisms);
        this.newOrganisms.clear();
        LOGGER.info(String.format("Turn %d ends", this.turnCounter));
        worldWriter.print(String.format("Turn %d ends", this.turnCounter));

        this.turnCounter++;
    }

    public void addOrganism(Organism organism) {
        this.board.addOrganism(organism);
        this.newOrganisms.add(organism);
        LOGGER.info(
                String.format(
                        "Organism %s is born at %s",
                        organism.name(),
                        organism.getPosition().toString()
                )
        );
        worldWriter.print(String.format(
                "Organism %s is born at %s",
                organism.name(),
                organism.getPosition().toString()
        ));
        LOGGER.info(String.format("%s was added", organism.toString()));
        worldWriter.print(String.format("%s was added", organism.toString()));
    }

    public void removeOrganism(Organism organism, DeathCause deathCause) {
        this.board.removeOrganism(organism);
        organism.kill();
        LOGGER.info(
                String.format(
                        "Organism %s at %s dies due to a cause '%s'",
                        organism.name(),
                        organism.getPosition().toString(),
                        deathCause
                )
        );
        worldWriter.print(String.format(
                "Organism %s at %s dies due to a cause '%s'",
                organism.name(),
                organism.getPosition().toString(),
                deathCause
        ));
        LOGGER.info(String.format("%s was removed", organism.toString()));
        worldWriter.print(String.format("%s was removed", organism.toString()));
    }

    public void moveOrganism(Organism organism, Position destination) {
        LOGGER.info(
                String.format(
                        "Organism %s is trying to move from %s to %s",
                        organism.name(),
                        organism.getPosition().toString(),
                        destination.toString()
                )
        );
        worldWriter.print(
                String.format(
                        "Organism %s is trying to move from %s to %s",
                        organism.name(),
                        organism.getPosition().toString(),
                        destination.toString()
                )
        );
        final Organism attacker = Objects.requireNonNull(organism);
        final Organism defender = this.board.getOrganism(destination);

        if (defender == null || defender.isDead()) {
            this.move(attacker, destination);
            LOGGER.info(
                String.format(
                    "Organism %s moved to an empty position %s",
                    attacker.name(),
                    destination.toString()
                )
            );
            worldWriter.print(
                    String.format(
                            "Organism %s moved to an empty position %s",
                            attacker.name(),
                            destination.toString()
                    )
            );
        } else if (!organism.getClass().equals(defender.getClass())) {
            LOGGER.info(
                String.format(
                    "Organism %s attacks organism %s at %s",
                    attacker.name(),
                    defender.name(),
                    destination.toString()
                )
            );
            worldWriter.print(
                    String.format(
                            "Organism %s attacks organism %s at %s",
                            attacker.name(),
                            defender.name(),
                            destination.toString()
                    )
            );
            final List<Action> actions = defender.defend(attacker);

            this.dispatchActions(actions);

            if (attacker.isAlive()) {
                this.move(attacker, destination);
                LOGGER.info(
                    String.format(
                        "Organism %s moved to a position %s after winning the battle",
                        attacker.name(),
                        destination.toString()
                    )
                );
                worldWriter.print(
                        String.format(
                                "Organism %s moved to a position %s after winning the battle",
                                attacker.name(),
                                destination.toString()
                        )
                );
            }
        } else {
            LOGGER.info(
                String.format(
                    "Organism %s did not move to %s because there was an organism of the same species",
                    attacker.name(),
                    destination.toString()
                )
            );
            worldWriter.print(
                    String.format(
                            "Organism %s did not move to %s because there was an organism of the same species",
                            attacker.name(),
                            destination.toString()
                    )
            );
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
        LOGGER.info(
                String.format(
                        "Freeze action was invoked at %s with radius %d",
                        center.toString(),
                        radius
                )
        );
        worldWriter.print(
                String.format(
                        "Freeze action was invoked at %s with radius %d",
                        center.toString(),
                        radius
                )
        );
        this.board.getNeighborOrganisms(center, radius)
            .forEach(o -> {
                o.freeze();
                LOGGER.info(String.format("%s was frozen", o.toString()));
                worldWriter.print(String.format("%s was frozen", o.toString()));
            });
    }

    public void unfreezeOrganisms(Position center, int radius) {
        LOGGER.info(
                String.format(
                        "Unfreeze action was invoked at %s with radius %d",
                        center.toString(),
                        radius
                )
        );
        worldWriter.print(
                String.format(
                        "Unfreeze action was invoked at %s with radius %d",
                        center.toString(),
                        radius
                )
        );
        this.board.getNeighborOrganisms (center, radius)
            .forEach(o -> {
                o.unfreeze();
                LOGGER.info(String.format("%s was unfrozen", o.toString()));
                worldWriter.print(String.format("%s was unfrozen", o.toString()));
            });
    }

    public void switchOnRandomEvents() {
        this.areRandomEvents = true;
    }

    public void switchOffRandomEvents() {
        this.areRandomEvents = false;
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

    private void setWorldWriter(char whichWriter) {
        UIFactory factory;
        if (Character.toLowerCase(whichWriter) == 'f') {
            factory = new FileUIFactory();
        }
        else{
            factory = new ConsoleUIFactory();
        }
        worldWriter = factory.createWriter();
    }

    private void move(Organism organism, Position destination) {
        this.board.removeOrganism(organism);
        organism.setPosition(destination);
        this.board.addOrganism(organism);
    }

    private void makeMoves() {
        this.forEachAliveOrganism(o -> o.move());
    }

    private void vitalizeOrganisms() {
        this.forEachAliveOrganism(o -> o.vitalize());
    }

    private void makeActions() {
        this.forEachAliveOrganism(o -> o.action());
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

    private void forEachAliveOrganism(Function<Organism, List<Action>> actionMaker) {
        this.organisms
            .stream()
            .filter(o -> o.isAlive() && !o.isFrozen())
            .forEach(o -> {
                final List<Action> actions = actionMaker.apply(o);

                this.dispatchActions(actions);
            });
    }

    private void dispatchActions(List<Action> actions) {
        actions.forEach(action -> action.accept(this.visitor));
    }
}
