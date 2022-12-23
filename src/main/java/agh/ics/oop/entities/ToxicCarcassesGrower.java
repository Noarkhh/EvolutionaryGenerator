package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.TileMap;

import java.util.HashMap;
import java.util.Random;

public class ToxicCarcassesGrower extends PlantGrower {

    public ToxicCarcassesGrower(TileMap tileMap, HashMap<Vector, Plant> plants) {
        super(tileMap, plants);
    }

    @Override
    public Vector getNewPlantPosition() {
        return null;
    }
}
