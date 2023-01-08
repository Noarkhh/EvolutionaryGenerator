package agh.ics.oop.config;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.variant_enums.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private final JSONObject configData;

    public Config(String configPath) throws IOException, ParseException {
        configData = (JSONObject) new JSONParser().parse(new FileReader(configPath));
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
        return configData.containsKey("startingPlants") ? ((Long) configData.get("startingPlants")).intValue() : 10;
    }

    public int getPlantEnergy() {
        return configData.containsKey("plantEnergy") ? ((Long) configData.get("plantEnergy")).intValue() : 10;
    }

    public int getDailyPlants() {
        return configData.containsKey("dailyPlants") ? ((Long) configData.get("dailyPlants")).intValue() : 3;
    }

    public PlantGrowthVariant getPlantGrowthVariant() {
        try {
            return PlantGrowthVariant.fromString((String) configData.get("plantGrowthVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return PlantGrowthVariant.TOXIC_CARCASSES;
        }
    }

    public int getStartingAnimals() {
        return configData.containsKey("startingAnimals") ? ((Long) configData.get("startingAnimals")).intValue() : 10;
    }

    public int getStartingAnimalEnergy() {
        return configData.containsKey("startingAnimalEnergy") ? ((Long) configData.get("startingAnimalEnergy")).intValue() : 20;
    }

    public int getSatiatedEnergy() {
        return configData.containsKey("satiatedEnergy") ? ((Long) configData.get("satiatedEnergy")).intValue() : 30;
    }

    public int getProcreationEnergyCost() {
        return configData.containsKey("procreationEnergyCost") ? ((Long) configData.get("procreationEnergyCost")).intValue() : 10;
    }

    public int getMinMutations() {
        return configData.containsKey("minMutations") ? ((Long) configData.get("minMutations")).intValue() : 1;
    }

    public int getMaxMutations() {
        return configData.containsKey("maxMutations") ? ((Long) configData.get("maxMutations")).intValue() : 3;
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
        return configData.containsKey("genomeLength") ? ((Long) configData.get("genomeLength")).intValue() : 8;
    }

    public BehaviorVariant getBehaviorVariant() {
        try {
            return BehaviorVariant.fromString((String) configData.get("behaviorVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return BehaviorVariant.FULL_PREDESTINATION;
        }
    }

    public RecombinationVariant getRecombinationVariant() {
        try {
            return RecombinationVariant.fromString((String) configData.get("recombinationVariant"));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return RecombinationVariant.DARWINIAN;
        }
    }

    public Long getMillisecondsBetweenStages() {
        return configData.containsKey("millisecondsBetweenStages") ? (Long) configData.get("millisecondsBetweenStages") : 500;
    }
}
