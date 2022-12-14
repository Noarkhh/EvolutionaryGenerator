package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.*;

import java.util.*;

public abstract class EntityMap implements IWorldMap<Entity>, IPositionObserver {
    private final Map<Vector, List<Entity>> entities;
    private final List<Animal> animals;
    private final Map<Vector, Plant> plants;
    private final List<Animal> graveyard;
    private final Map<Vector, Integer> deathSpots;

    public final Vector size;

    public EntityMap(Config config, EntitiesContainer entitiesContainer) {
        this.entities = entitiesContainer.getEntities();
        this.animals = entitiesContainer.getAnimals();
        this.plants = entitiesContainer.getPlants();
        this.graveyard = entitiesContainer.getGraveyard();
        this.deathSpots = entitiesContainer.getDeathSpots();
        size = config.getMapSize();

    }

    public Map<Vector, List<Entity>> getEntities() {
        return Collections.unmodifiableMap(entities);
    }

    private boolean contains(Vector position) {
        return 0 <= position.x && position.x < size.x && 0 <= position.y && position.y < size.y;
    }

    protected abstract void bringBackToBounds(Animal movedAnimal);

    @Override
    public void positionChanged(Animal movedAnimal, Vector oldPosition, Vector newPosition) {
        entities.get(oldPosition).remove(movedAnimal);
        if (entities.get(oldPosition).isEmpty()) entities.remove(oldPosition);
        if (entities.containsKey(newPosition)) {
            entities.get(newPosition).add(movedAnimal);
        } else {
            entities.put(newPosition, new LinkedList<>(List.of(movedAnimal)));
        }
        if (!contains(movedAnimal.getPosition())) bringBackToBounds(movedAnimal);
    }

    @Override
    public void removedFrom(Entity removedEntity, Vector position) {
        remove(position, removedEntity);
        if (removedEntity instanceof Animal animal && !animal.isAlive()) {
            graveyard.add(animal);
            if (!deathSpots.containsKey(position)) {
                deathSpots.put(position, 1);
            } else {
                deathSpots.put(position, deathSpots.get(position) + 1);
            }
        }
    }

    @Override
    public List<Entity> objectsAt(Vector position) {
        return entities.get(position);
    }

    @Override
    public void place(Entity objectToPlace) {
        if (entities.containsKey(objectToPlace.getPosition())) {
            entities.get(objectToPlace.getPosition()).add(objectToPlace);
        } else {
            entities.put(objectToPlace.getPosition(), new LinkedList<>(List.of(objectToPlace)));
        }
        if (objectToPlace instanceof Animal) animals.add((Animal) objectToPlace);
        if (objectToPlace instanceof Plant) plants.put(objectToPlace.getPosition(), (Plant) objectToPlace);
    }

    @Override
    public boolean remove(Vector position, Entity objectToRemove) {
        List<Entity> entitiesAtRemovePosition = entities.get(objectToRemove.getPosition());
        if (entitiesAtRemovePosition == null) return false;
        if (!entitiesAtRemovePosition.remove(objectToRemove)) return false;
        if (entitiesAtRemovePosition.isEmpty()) entities.remove(objectToRemove.getPosition());
        if (objectToRemove instanceof Animal) animals.remove((Animal) objectToRemove);
        if (objectToRemove instanceof Plant) plants.remove(objectToRemove.getPosition());
        return true;
    }

    @Override
    public void remove(Entity objectToRemove) {
        remove(objectToRemove.getPosition(), objectToRemove);
    }

    @Override
    public String toString() {
        return entities.toString();
    }
}
