package Project;

public class Position {
    private int positionX;
    private int positionY;
    Position(int positionX, int positionY){
        setPositionX(positionX);
        setPositionY(positionY);
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        if (positionX < 0){
            throw new IllegalArgumentException("Negative X position.");
        }
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        if (positionY < 0){
            throw new IllegalArgumentException("Negative Y position.");
        }
        this.positionY = positionY;
    }
    public boolean equals(Position o){
        if (o.getPositionX() == this.getPositionX() && o.getPositionY() == this.getPositionY()){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return String.format(positionX + ", " + positionY);
    }
}
