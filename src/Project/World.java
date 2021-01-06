package Project;

import java.util.*;

public class World {
    private final int width;
    private final int height;
    private int turnCounter = 0;
    private final char separator = '.';
    private final Map<Position, Organism> map;
    private final Set<Organism> organisms = new TreeSet<>(new Comparator<Organism>() {
        @Override
        public int compare(Organism o1, Organism o2) {
            if (o1.getInitiative() > o2.getInitiative()) {
                return 1;
            }

            if (o2.getInitiative() < o2.getInitiative()) {
                return -1;
            }

            return 0;
        }
    });
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
        if (turnCounter == 0){
            drawMap();
        }
        for (Organism o : this.organisms) {
            List<Action> actionsMove = o.move();

            this.dispatchActions(actionsMove);
        }

        for (Organism o : this.organisms) {
            o.vitals();
        }

        clearDead();

        for (Organism o : this.organisms) {
            List<Action> actionsAct = o.action();

            this.dispatchActions(actionsAct);
        }

        this.turnCounter++;
        drawMap();
    }

    public void move(Organism organism, Position destination) {
        if (this.isPositionCorrect(destination)) {
            Organism defender = this.map.get(destination);

            if (defender == null) {
                this.moveOrganism(organism, destination);
            } else if (!organism.getClass().equals(defender.getClass())) {
                List<Action> actions = defender.defend(organism);

                this.dispatchActions(actions);

                if (organism.isAlive()) {
                    this.moveOrganism(organism, destination);
                }
            }
        }
    }

    public void addOrganism(Organism organism) {
        Position position = organism.getPosition();

        if (this.isPositionCorrect(position) && this.isPositionFree(position)) {
            this.map.put(position, organism);
            this.organisms.add(organism);
        }
    }

    public List<Position> getPossibleMovePositions(Organism organism) {
        ArrayList<Position> positions = new ArrayList<>();
        Position position = organism.getPosition();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                Position p = new Position(position.getX() + dx, position.getY() + dy);

                if (this.isPositionCorrect(p)) {
                    positions.add(p);
                }
            }
        }

        return positions;
    }

    public List<Position> getNeighboringFreePositions(Organism organism) {
        ArrayList<Position> positions = new ArrayList<>();
        Position position = organism.getPosition();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                Position p = new Position(position.getX() + dx, position.getY() + dy);

                if (this.isPositionCorrect(p) && this.isPositionFree(p)) {
                    positions.add(p);
                }
            }
        }

        return positions;
    }

    private boolean isPositionCorrect(Position position) {
        return this.map.containsKey(position);
    }

    private boolean isPositionFree(Position position) {
        return this.map.get(position) == null;
    }

    private Organism getOrganism(Position position) {
        return this.map.get(position);
    }

    private void dispatchActions(List<Action> actions) {
        for (Action a : actions) {
            a.accept(this.visitor);
        }
    }

    private void moveOrganism(Organism organism, Position destination) {
        this.map.put(organism.getPosition(), null);
        this.map.put(destination, organism);
        organism.setPosition(destination);
    }

    private void clearDead(){
        Iterator <Organism> i = organisms.iterator();
        while (i.hasNext()){
            Organism o = i.next();
            if (!o.isAlive()){
                Position position = o.getPosition();
                i.remove();
                this.map.put(position, null);
            } 
        }
    }  

    private void drawMap(){
        System.out.println(turnCounter);
        for (int i = 0; i < this.width + 2; i++){
            System.out.print('-');
        }
        System.out.println();
        for (int y = 0; y < this.height + 2; y++){
            System.out.print('|');
            for (int x = 0; x < this.width + 2; x++) {
                if (x < width && y < height) {
                    Position position = new Position(x, y);
                    if (isPositionFree(position)) {
                        System.out.print(" ");
                    } else {
                        System.out.print(getOrganism(position).getSign());
                    }
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print('|');
            System.out.println();
        }
        for (int i = 0; i < this.width + 2; i++){
            System.out.print('-');
        }
        System.out.println();
    }
}
