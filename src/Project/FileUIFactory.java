package Project;

public class FileUIFactory implements  UIFactory {
    @Override
    public WorldWriter createWriter() {
        return new FileWorldWriter();
    }
}
