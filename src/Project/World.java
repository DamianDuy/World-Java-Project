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
}
