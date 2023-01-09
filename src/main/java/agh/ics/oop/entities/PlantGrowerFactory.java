package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;

import java.util.HashMap;
import java.util.Map;

public class PlantGrowerFactory {
    public static PlantGrower createPlantGrower(Config config, HashMap<Vector, Plant> plants, Map<Vector, Integer> deathSpots) {
        return switch (config.getPlantGrowthVariant()) {
            case OVERGROWN_EQUATORS -> new OvergrownEquatorsGrower(config, plants);
            case TOXIC_CARCASSES -> new ToxicCarcassesGrower(config, plants, deathSpots);
        };
    }
}
