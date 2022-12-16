package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.MapDirection;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.EntityMap;

public class Animal extends Entity {

    private MapDirection direction = MapDirection.getRandom();

    public Animal(Vector position, EntityMap entityMap, Config config) {
        super(position, entityMap);
        image = imageContainer.getImage(this);
    }

    public MapDirection getDirection() {
        return direction;
    }
}
