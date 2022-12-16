package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.TileMap;

import java.util.Random;

public class ToxicCarcassesGrower implements PlantGrower {
    private final TileMap tileMap;
    private final Random RNG = new Random();

    public ToxicCarcassesGrower(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    @Override
    public Vector getNewPlantPosition() {
        return null;
    }
}
