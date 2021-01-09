package Project;

public class ConsoleUIFactory implements UIFactory {
    @Override
    public WorldWriter createWriter() {
        return new ConsoleWorldWrite();
    }
}
