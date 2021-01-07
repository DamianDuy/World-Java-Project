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
    public static final String DEBUG_LOGGER = "DebugLogger";
    private static final Logger LOGGER = Logger.getLogger(Main.DEBUG_LOGGER);

    public static void main(String args[]) {
        configureLogger();

        final int turns = 250;
        final World world = new World(24, 8);
        final List<Organism> initialOrganisms = Arrays.asList(
            new Sheep(world, new Position(0, 0)),
            new Sheep(world, new Position(6, 7)),
            new Sheep(world, new Position(14, 2)),
            new Wolf(world, new Position(4, 5)),
            new Wolf(world, new Position(22, 1)),
            new Grass(world, new Position(5, 5)),
            new Grass(world, new Position(18, 7)),
            new Grass(world, new Position(23, 5)),
            new Dandelion(world, new Position(12, 2)),
            new Dandelion(world, new Position(1, 7)),
            new Toadstool(world, new Position(10, 1)),
            new Toadstool(world, new Position(17, 7))
        );

        try {
            initialOrganisms.forEach(o -> world.addOrganism(o));

            world.start();

            for (int i = 0; i < turns; i++) {
                world.makeTurn();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);

            // Rethrow the exception so that it will halt the program rather
            // than ignore it.
            throw e;
        }
    }

    private static void configureLogger() {
        FileInputStream config = null;

        try {
            final FileHandler fileHandler = new FileHandler("./logs/debug.log");
            config = new FileInputStream("logger.config");

            LogManager.getLogManager().readConfiguration(config);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
