package agh.ics.oop.simulation;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.genes.Genome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistics {
    private final EntitiesContainer entitiesContainer;
    private final Config config;
    private int numberOfAnimals;
    private int numberOfPlants;
    private int freeTiles;
    private List<Genome> mostPopularGenomes;
    private float averageEnergy;
    private float averageLifespan;


    public Statistics(Config config, EntitiesContainer entitiesContainer) {
        this.entitiesContainer = entitiesContainer;
        this.config = config;
        refresh();
    }

    public void refresh() {
        numberOfAnimals = entitiesContainer.getAnimals().size();
        numberOfPlants = entitiesContainer.getPlants().size();
        freeTiles = config.getMapSize().x * config.getMapSize().y - entitiesContainer.getEntities().size();
        averageEnergy = calculateAverageEnergy();
        averageLifespan = calculateAverageLifespan();
    }

//    private List<Genome> calculateMostPopularGenomes() {
//        Map<Genome, Integer> genomeOccurrences = new HashMap<>();
//        for (Animal animal : entitiesContainer.getAnimals()) {
//            animal.genome.getGenes();
//        }
//        return
//    }

    private float calculateAverageEnergy() {
        float energySum = 0;
        for (Animal animal : entitiesContainer.getAnimals()) {
            energySum += animal.getEnergy();
        }
        return energySum / entitiesContainer.getAnimals().size();
    }

    private float calculateAverageLifespan() {
        float lifespanSum = 0;
        for (Animal animal : entitiesContainer.getGraveyard()) {
            lifespanSum += animal.getEnergy();
        }
        return lifespanSum / entitiesContainer.getGraveyard().size();
    }
}
