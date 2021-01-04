package Project;

import java.util.ArrayList;

public class World {
    private int worldX;
    private int worldY;
    private int turn;
    private ArrayList<Organism> organisms = new ArrayList<Organism>();
    private ArrayList<Organism> newOrganisms = new ArrayList<Organism>();
    private char separator = '.';
    World(int worldX, int worldY){
        setTurn();
        setWorldX(worldX);
        setWorldY(worldY);
    }
    public void setTurn(){
        this.turn = 0;
    }
    public void setWorldX(int worldX){
        if (worldX < 0){
            throw new IllegalArgumentException("Negative X size.");
        }
        this.worldX = worldX;
    }
    public void setWorldY(int worldY){
        if (worldY < 0){
            throw new IllegalArgumentException("Negative Y size.");
        }
        this.worldY = worldY;
    }



    public boolean ifCorrectPosition(Position position){
        return position.getPositionX() >= 0 && position.getPositionY() >= 0 && position.getPositionX() < worldX
                && position.getPositionY() < worldY;
    }

    public ArrayList<Position> getNeighboringPositions(Position position){
        ArrayList<Position> positions = new ArrayList<>();
        for (int x = -1; x < 2; x++)
            for (int y = -1; y < 2; y++){
                Position pomPosition = new Position(position.getPositionX() + x,
                        position.getPositionY() + y);
                if (ifCorrectPosition(pomPosition) && !(x == 0 && y == 0)){
                    positions.add(pomPosition);
                }
            }
        return positions;
    }

}
