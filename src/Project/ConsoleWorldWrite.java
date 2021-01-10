package Project;

public class ConsoleWorldWrite implements WorldWriter {
    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
