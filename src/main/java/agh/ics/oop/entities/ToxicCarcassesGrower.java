package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;

import java.util.HashMap;

public class ToxicCarcassesGrower extends PlantGrower {

    public ToxicCarcassesGrower(HashMap<Vector, Plant> plants) {
        super(plants);
    }

    @Override
    public Vector getNewPlantPosition() {
        return null;
    }
}
