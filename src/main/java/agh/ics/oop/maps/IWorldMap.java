package agh.ics.oop.maps;

import agh.ics.oop.core_classes.Vector;

import java.util.List;

public interface IWorldMap<T> {
    List<T> objectsAt(Vector position);
    void place(T objectToPlace);
    boolean remove(Vector position, T objectToRemove);
    void remove(T objectToRemove);
}
