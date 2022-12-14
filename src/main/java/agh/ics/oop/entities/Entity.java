package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.ImageContainer;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.EntityMap;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public abstract class Entity extends Sprite {

    private final List<IPositionObserver> observers = new LinkedList<>();
    private final EntityMap entityMap;

    protected Entity(Vector position, EntityMap entityMap) {
        super(position);
        this.entityMap = entityMap;
        observers.add(entityMap);
    }

    private void removed() {
        for (IPositionObserver observer : observers) {
            observer.removedFrom(this, position);
        }
    }
}
