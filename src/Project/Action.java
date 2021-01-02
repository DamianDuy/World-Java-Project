package Project;

public class Action {
    private ActionEnum actionEnum;
    private Position position;
    private int value;
    private Organism organism;
    Action(ActionEnum actionEnum, Position position, int value, Organism organism){
        this.actionEnum = actionEnum;
        this.position = position;
        setValue(value);
        this.organism = organism;
    }

    public void setValue(int value) {
        if (value < 0){
            throw new IllegalArgumentException("Value is negative.");
        }
        this.value = value;
    }

    @Override
    public String toString(){
        switch (actionEnum.getValue()){
            case 0:
                return String.format("Action: move from: " + organism.getPosition() + " to: " + position);
            case 1:
                return String.format("Action: remove from: " + organism.getPosition());
            case 2:
                return String.format("Action: add at: " + position);
            case 3:
                return String.format("Action: increase power: " + value);
        }
        return "Action not set.";
    }
}
