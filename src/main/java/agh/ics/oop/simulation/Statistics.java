package agh.ics.oop.simulation;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.genes.Genome;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.*;

public class Statistics {
    private final EntitiesContainer entitiesContainer;
    private final Config config;

    private final GridPane grid = new GridPane();


    public Statistics(Config config, EntitiesContainer entitiesContainer) {
        this.entitiesContainer = entitiesContainer;
        this.config = config;
        setupGrid();
        refresh();

    }

    public void refresh() {
        grid.getChildren().subList(7, grid.getChildren().size()).clear();

        grid.add(new Label(Integer.toString(entitiesContainer.getAnimals().size())), 1, 0);
        grid.add(new Label(Integer.toString(entitiesContainer.getPlants().size())), 1, 1);
        grid.add(new Label(Integer.toString(calculateEmptyTiles())), 1, 2);
        grid.add(new Label(calculateMostPopularGenome().toString()), 1, 3);
        grid.add(new Label(Float.toString(calculateAverageEnergy())), 1, 4);
        grid.add(new Label(Float.toString(calculateAverageLifespan())), 1, 5);
    }

    private void setupGrid() {
        grid.setGridLinesVisible(true);
        grid.add(new Label("Number of animals"), 0, 0);
        grid.add(new Label("Number of plants"), 0, 1);
        grid.add(new Label("Number of empty tiles"), 0, 2);
        grid.add(new Label("Most popular genome"), 0, 3);
        grid.add(new Label("Average animal energy"), 0, 4);
        grid.add(new Label("Average animal lifespan"), 0, 5);

    }

    private int calculateEmptyTiles() {
        return config.getMapSize().x * config.getMapSize().y - entitiesContainer.getEntities().size();
    }

    private Genome calculateMostPopularGenome() {
        Map<Genome, Integer> genomeOccurrences = new HashMap<>();
        for (Animal animal : entitiesContainer.getAnimals()) {
            if (genomeOccurrences.containsKey(animal.genome)) {
                genomeOccurrences.put(animal.genome, genomeOccurrences.get(animal.genome) + 1);
            } else {
                genomeOccurrences.put(animal.genome, 1);
            }
        }
        return Collections.max(genomeOccurrences.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

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
            lifespanSum += animal.getAge();
        }
        return lifespanSum / entitiesContainer.getGraveyard().size();
    }

    public GridPane getGrid() {
        return grid;
    }
}
