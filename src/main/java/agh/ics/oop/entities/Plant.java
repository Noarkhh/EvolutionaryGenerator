package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.EntityMap;

public class Plant extends Entity {
    private int energy;
    public Plant(Vector position, EntityMap entityMap, Config config) {
        super(position, entityMap);
        image = imageContainer.getImage(this);
        energy = config.getPlantEnergy();
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "Plant(e: " + energy + ", " + position + ")";
    }
}
