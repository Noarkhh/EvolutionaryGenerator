package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;

import java.util.HashMap;
import java.util.Random;

public abstract class PlantGrower {
    protected final HashMap<Vector, Plant> plants;
    protected final Random RNG = new Random();

    protected PlantGrower(HashMap<Vector, Plant> plants) {
        this.plants = plants;
    }

    public abstract Vector getNewPlantPosition();
}
