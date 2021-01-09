package Project;

import java.io.FileWriter;
import java.io.IOException;

public class FileWorldWriter implements WorldWriter {
    public void print(String string) {
        try {
            final FileWriter writeToFile = new FileWriter("worldProgress.txt");
            writeToFile.write(string);
            writeToFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
