package Project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String args[]) {

        final int turns = 25;
        final World world = new World(14, 6, 'c');
        final List<Organism> initialOrganisms = Arrays.asList(
            new Sheep(world, new Position(0, 0)),
            new Sheep(world, new Position(6, 4)),
            new Sheep(world, new Position(8, 2)),
            new Wolf(world, new Position(4, 5)),
            new Wolf(world, new Position(9, 1)),
            new Grass(world, new Position(5, 5)),
            new Grass(world, new Position(12, 4)),
            new Grass(world, new Position(11, 5)),
            new Dandelion(world, new Position(12, 2)),
            new Dandelion(world, new Position(1, 5)),
            new Toadstool(world, new Position(10, 1)),
            new Toadstool(world, new Position(13, 5))
        );

        try {
            initialOrganisms.forEach(o -> world.addOrganism(o));

            world.start();

            for (int i = 0; i < turns; i++) {
                world.makeTurn();
            }
        } catch (Exception e) {

            // Rethrow the exception so that it will halt the program rather
            // than ignore it.
            throw e;
        }
    }

}
