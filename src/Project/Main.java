package Project;

public class Main {
    public static void main(String args[]) {
        World world = new World(5, 5);
        Position position1 = new Position(4, 4);
        Position position2 = new Position(0, 0);
        Position position3 = new Position(3, 3);
        Toadstool toadstool = new Toadstool(world, position1);
        Sheep sheep = new Sheep(world, position2);
        Wolf wolf = new Wolf(world, position3);

        world.addOrganism(toadstool);
        world.addOrganism(sheep);
        world.addOrganism(wolf);
        System.out.println("Initial");
        world.print();
        world.start();

        for (int i = 0; i < 40; i++) {
            world.makeTurn();
        }
    }
}
