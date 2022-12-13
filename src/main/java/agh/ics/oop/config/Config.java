package agh.ics.oop.config;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.variant_enums.BehaviorVariant;
import agh.ics.oop.variant_enums.MapVariant;
import agh.ics.oop.variant_enums.MutationsVariant;
import agh.ics.oop.variant_enums.PlantGrowthVariant;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Config {
    private final JSONObject configData;

    public Config() throws Exception {
        configData = (JSONObject) new JSONParser().parse(new FileReader("config/config1.json"));
    }

    public Vector getMapSize() {
        return new Vector(((Long) configData.get("mapWidth")).intValue(), ((Long) configData.get("mapHeight")).intValue());
    }

    public MapVariant getMapVariant() {
        try {
            return MapVariant.fromString((String) configData.get("mapVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return MapVariant.GLOBE;
        }
    }

    public int getStartingPlants() {
        return ((Long) configData.get("startingPlants")).intValue();
    }

    public int getPlantEnergy() {
        return ((Long) configData.get("plantEnergy")).intValue();
    }

    public int getDailyPlants() {
        return ((Long) configData.get("dailyPlants")).intValue();
    }

    public PlantGrowthVariant getPlantGrowthVariant() {
        try {
            return PlantGrowthVariant.fromString((String) configData.get("plantGrowthVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return PlantGrowthVariant.TOXIC_CARCASSES;
        }
    }

    public int getStarringAnimals() {
        return ((Long) configData.get("startingAnimals")).intValue();
    }

    public int getStartingAnimalEnergy() {
        return ((Long) configData.get("startingAnimalEnergy")).intValue();
    }

    public int getSatiatedEnergy() {
        return ((Long) configData.get("satiatedEnergy")).intValue();
    }

    public int getProcreationEnergyCost() {
        return ((Long) configData.get("procreationEnergyCost")).intValue();
    }

    public int getMinMutations() {
        return ((Long) configData.get("minMutations")).intValue();
    }

    public int getMaxMutations() {
        return ((Long) configData.get("maxMutations")).intValue();
    }

    public MutationsVariant getMutationsVariant() {
        try {
            return MutationsVariant.fromString((String) configData.get("mutationsVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return MutationsVariant.FULL_RANDOM;
        }
    }

    public int getGenomeLength() {
        return ((Long) configData.get("minMutations")).intValue();
    }

    public BehaviorVariant getBehaviorVariant() {
        try {
            return BehaviorVariant.fromString((String) configData.get("behaviorVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return BehaviorVariant.FULL_PREDESTINATION;
        }
    }
}
