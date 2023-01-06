package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;

import java.util.HashMap;

public class PlantGrowerFactory {
    public static PlantGrower createPlantGrower(Config config, HashMap<Vector, Plant> plants) {
        return switch (config.getPlantGrowthVariant()) {
            case OVERGROWN_EQUATORS -> new OvergrownEquatorsGrower(config, plants);
            case TOXIC_CARCASSES -> new ToxicCarcassesGrower(plants);
        };
    }
}
