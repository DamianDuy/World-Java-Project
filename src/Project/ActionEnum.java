package Project;

public enum ActionEnum {
    A_MOVE(0), A_REMOVE(1), A_ADD(2), A_INCREASEPOWER(3);
    private final int value;
    private ActionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
