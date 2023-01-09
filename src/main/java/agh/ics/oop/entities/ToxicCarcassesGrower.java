package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;

import java.util.*;
import java.util.Map.Entry;

public class ToxicCarcassesGrower extends PlantGrower {
    private final Config config;
    private final Map<Vector, Integer> deathSpots;

    public ToxicCarcassesGrower(Config config, HashMap<Vector, Plant> plants, Map<Vector, Integer> deathSpots) {
        super(plants);
        this.config = config;
        this.deathSpots = deathSpots;
    }

    @Override
    public Vector getNewPlantPosition() {
        List<Entry<Vector, Integer>> deathSpotsList = new ArrayList<>(deathSpots.entrySet());
        deathSpotsList.sort(Comparator.comparingInt(Entry::getValue));

        int numberOfToxicTiles = (int) ((config.getMapSize().x * config.getMapSize().y) * 0.2);

        List<Vector> toxicTiles = new ArrayList<>();
        for (int i = 0; i < numberOfToxicTiles; i++) {
            if (i < deathSpotsList.size()) {
                toxicTiles.add(deathSpotsList.get(deathSpotsList.size() - i - 1).getKey());
            } else {
                toxicTiles.add(new Vector(RNG.nextInt(config.getMapSize().x), RNG.nextInt(config.getMapSize().y)));
            }
        }

        Vector newPosition;
        boolean growOnCarcass = RNG.nextInt(5) == 0;
        int attempts = 10;
        do {
            if (growOnCarcass) {
                newPosition = toxicTiles.get(RNG.nextInt(toxicTiles.size()));
            } else {
                newPosition = new Vector(RNG.nextInt(config.getMapSize().x), RNG.nextInt(config.getMapSize().y));
            }
            attempts--;
        } while (plants.containsKey(newPosition) && attempts >= 0);
        return newPosition;
    }
}
