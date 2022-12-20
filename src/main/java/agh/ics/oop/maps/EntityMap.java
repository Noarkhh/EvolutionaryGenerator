package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.*;
import agh.ics.oop.genes.Genome;
import agh.ics.oop.genes.GenomeFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public abstract class EntityMap implements IWorldMap<Entity>, IPositionObserver {
    private final HashMap<Vector, List<Entity>> entities = new HashMap<>();
    private final List<Animal> animals = new LinkedList<>();
    private final List<Plant> plants = new LinkedList<>();

    private final PlantGrower plantGrower;

    public final Vector size;
    private final Config config;

    public EntityMap(Config config, TileMap tileMap) {
        this.config = config;
        size = config.getMapSize();
        this.plantGrower = switch (config.getPlantGrowthVariant()) {
            case TOXIC_CARCASSES -> new ToxicCarcassesGrower(tileMap);
            case OVERGROWN_EQUATORS -> new OvergrownEquatorsGrower(tileMap);
        };

        for (int i = 0; i < config.getStartingAnimals(); i++) spawnAnimal();
        for (int i = 0; i < config.getStartingPlants(); i++) growPlant();
    }

    public void spawnAnimal() {
        Random random = new Random();
        place(new Animal(new Vector(random.nextInt(size.x), random.nextInt(size.y)), this, config, GenomeFactory.createGenome(config), config.getStartingAnimalEnergy()));
    }

    public void growPlant() {
        place(new Plant(plantGrower.getNewPlantPosition(), this, config));
    }

    private boolean contains(Vector position) {
        return 0 <= position.x && position.x < size.x && 0 <= position.y && position.y < size.y;
    }

    protected abstract void bringBackToBounds(Animal movedAnimal);

    @Override
    public void positionChanged(Animal movedAnimal, Vector oldPosition, Vector newPosition) {
        remove(oldPosition, movedAnimal);
        place(movedAnimal);
    }

    @Override
    public void removedFrom(Entity removedEntity, Vector position) {
        remove(position, removedEntity);
    }

    @Override
    public List<Entity> objectsAt(Vector position) {
        return entities.get(position);
    }

    @Override
    public void place(Entity objectToPlace) {
        if (!contains(objectToPlace.getPosition())) bringBackToBounds((Animal) objectToPlace);

        if (entities.containsKey(objectToPlace.getPosition())) {
            entities.get(objectToPlace.getPosition()).add(objectToPlace);
        } else {
            entities.put(objectToPlace.getPosition(), new LinkedList<>(List.of(objectToPlace)));
        }
        if (objectToPlace instanceof Animal) animals.add((Animal) objectToPlace);
        else if (objectToPlace instanceof Plant) plants.add((Plant) objectToPlace);
    }

    @Override
    public boolean remove(Vector position, Entity objectToRemove) {
        List<Entity> entitiesAtRemovePosition = entities.get(objectToRemove.getPosition());
        if (entitiesAtRemovePosition == null) return false;
        if (!entitiesAtRemovePosition.remove(objectToRemove)) return false;
        if (entitiesAtRemovePosition.isEmpty()) entities.remove(objectToRemove.getPosition());
        return true;
    }

    @Override
    public boolean remove(Entity objectToRemove) {
        return remove(objectToRemove.getPosition(), objectToRemove);
    }
}
