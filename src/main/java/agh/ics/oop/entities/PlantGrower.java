package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.TileMap;

import java.util.HashMap;
import java.util.Random;

public abstract class PlantGrower {
    protected final TileMap tileMap;
    protected final HashMap<Vector, Plant> plants;
    protected final Random RNG = new Random();

    protected PlantGrower(TileMap tileMap, HashMap<Vector, Plant> plants) {
        this.tileMap = tileMap;
        this.plants = plants;
    }

    public abstract Vector getNewPlantPosition();
}
