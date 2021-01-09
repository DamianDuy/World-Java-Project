package Project;

import java.io.*;

public class FileWorldWriter implements WorldWriter {
    private boolean alreadyExecuted = false;
    private final String filename = "worldProgress.txt";
    public void print(String string) {
        if (!alreadyExecuted) {
            if (new File(filename).isFile()) {
                File f = new File(filename);
                f.delete();
            }
            alreadyExecuted = true;
        }
        try {
            final PrintStream writeToFile = new PrintStream( new FileOutputStream(
                    filename ,true));
            writeToFile.println(string);
            writeToFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
