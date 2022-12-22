package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.TileMap;

import java.util.HashMap;
import java.util.Random;

public class ToxicCarcassesGrower implements PlantGrower {
    private final TileMap tileMap;
    private final HashMap<Vector, Plant> plants;
    private final Random RNG = new Random();

    public ToxicCarcassesGrower(TileMap tileMap, HashMap<Vector, Plant> plants) {
        this.tileMap = tileMap;
        this.plants = plants;
    }

    @Override
    public Vector getNewPlantPosition() {
        return null;
    }
}
