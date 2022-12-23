package agh.ics.oop.entities;

import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.EntityMap;

import java.util.LinkedList;
import java.util.List;

public abstract class Entity extends Sprite {

    protected final List<IPositionObserver> observers = new LinkedList<>();
    protected final EntityMap entityMap;

    protected Entity(Vector position, EntityMap entityMap) {
        super(position);
        this.entityMap = entityMap;
        addObserver(entityMap);
    }

    protected void remove() {
        for (IPositionObserver observer : observers) {
            observer.removedFrom(this, position);
        }
    }

    public void addObserver(IPositionObserver observer) {
        observers.add(observer);
    }
}
