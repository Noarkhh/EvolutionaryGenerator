package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EntitiesContainer {
    private final HashMap<Vector, List<Entity>> entities = new HashMap<>();
    private final List<Animal> animals = new LinkedList<>();
    private final HashMap<Vector, Plant> plants = new HashMap<>();
    private final List<Animal> graveyard = new LinkedList<>();
    private final Map<Vector, Integer> deathSpots = new HashMap<>();

    public HashMap<Vector, List<Entity>> getEntities() {
        return entities;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public HashMap<Vector, Plant> getPlants() {
        return plants;
    }

    public List<Animal> getGraveyard() {
        return graveyard;
    }

    public Map<Vector, Integer> getDeathSpots() {
        return deathSpots;
    }
}
