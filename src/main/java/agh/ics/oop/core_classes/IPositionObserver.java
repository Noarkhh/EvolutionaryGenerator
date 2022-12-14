package agh.ics.oop.core_classes;

import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.Entity;

public interface IPositionObserver {
    void positionChanged(Animal movedAnimal, Vector oldPosition, Vector newPosition);
    void removedFrom(Entity removedEntity, Vector position);
}
