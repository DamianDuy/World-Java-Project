package Project;

public class Main {
    public static void main(String args[]) {
        World world = new World(10, 10);
        Position position1 = new Position(5, 5);
        Position position2 = new Position(0, 0);
        Position position3 = new Position(3, 3);
        Grass grass = new Grass(world, position1);
        Sheep sheep = new Sheep(world, position2);
        Wolf wolf = new Wolf(world, position3);
        world.addOrganism(grass);
        world.addOrganism(sheep);
        world.addOrganism(wolf);
        for (int i = 0; i < 20; i++) {
            world.makeTurn();
        }
    }
}
