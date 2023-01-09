package agh.ics.oop.simulation;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.genes.Genome;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.util.*;

public class SimulationStatistics implements Statistics {
    private final EntitiesContainer entitiesContainer;
    private final Config config;
    private Integer numberOfAnimals;
    private Integer numberOfPlants;
    private Integer numberOfEmptyTiles;
    private Genome mostPopularGenome;
    private Float averageEnergy;
    private Float averageLifespan;


    private final GridPane grid = new GridPane();


    public SimulationStatistics(Config config, EntitiesContainer entitiesContainer) {
        this.entitiesContainer = entitiesContainer;
        this.config = config;
        setupGrid();
        refresh();

    }

    @Override
    public void refresh() {
        grid.getChildren().subList(7, grid.getChildren().size()).clear();

        calculateNumberOfAnimals();
        calculateNumberOfPlants();
        calculateEmptyTiles();
        calculateMostPopularGenome();
        calculateAverageEnergy();
        calculateAverageLifespan();

        grid.add(new Text(Integer.toString(entitiesContainer.getAnimals().size())), 1, 0);
        grid.add(new Text(Integer.toString(entitiesContainer.getPlants().size())), 1, 1);
        grid.add(new Text(Integer.toString(numberOfEmptyTiles)), 1, 2);
        grid.add(new Text(mostPopularGenome.toString()), 1, 3);
        grid.add(new Text(Float.toString(averageEnergy)), 1, 4);
        grid.add(new Text(Float.toString(averageLifespan)), 1, 5);
    }

    private void setupGrid() {
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(200));
        grid.getColumnConstraints().add(new ColumnConstraints(200));
        for (int i = 0; i < 6; i++) grid.getRowConstraints().add(new RowConstraints(30));
        grid.add(new Text("Number of animals"), 0, 0);
        grid.add(new Text("Number of plants"), 0, 1);
        grid.add(new Text("Number of empty tiles"), 0, 2);
        grid.add(new Text("Most popular genome"), 0, 3);
        grid.add(new Text("Average animal energy"), 0, 4);
        grid.add(new Text("Average animal lifespan"), 0, 5);
    }

    private void calculateNumberOfAnimals() {
        numberOfAnimals = entitiesContainer.getAnimals().size();
    }

    private void calculateNumberOfPlants() {
        numberOfPlants = entitiesContainer.getPlants().size();
    }


    private void calculateEmptyTiles() {
        numberOfEmptyTiles = config.getMapSize().x * config.getMapSize().y - entitiesContainer.getEntities().size();
    }

    private void calculateMostPopularGenome() {
        Map<Genome, Integer> genomeOccurrences = new HashMap<>();
        for (Animal animal : entitiesContainer.getAnimals()) {
            if (genomeOccurrences.containsKey(animal.genome)) {
                genomeOccurrences.put(animal.genome, genomeOccurrences.get(animal.genome) + 1);
            } else {
                genomeOccurrences.put(animal.genome, 1);
            }
        }
        mostPopularGenome = Collections.max(genomeOccurrences.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }

    private void calculateAverageEnergy() {
        float energySum = 0;
        for (Animal animal : entitiesContainer.getAnimals()) {
            energySum += animal.getEnergy();
        }
        averageEnergy = energySum / entitiesContainer.getAnimals().size();
    }

    private void calculateAverageLifespan() {
        if (entitiesContainer.getGraveyard().size() == 0) {
            averageLifespan = 0.0F;
            return;
        }
        float lifespanSum = 0;
        for (Animal animal : entitiesContainer.getGraveyard()) {
            lifespanSum += animal.getAge();
        }
        averageLifespan = lifespanSum / entitiesContainer.getGraveyard().size();
    }


    public GridPane getGrid() {
        return grid;
    }

    public Integer getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public Integer getNumberOfPlants() {
        return numberOfPlants;
    }

    public Integer getNumberOfEmptyTiles() {
        return numberOfEmptyTiles;
    }

    public Genome getMostPopularGenome() {
        return mostPopularGenome;
    }

    public Float getAverageEnergy() {
        return averageEnergy;
    }

    public Float getAverageLifespan() {
        return averageLifespan;
    }
}
