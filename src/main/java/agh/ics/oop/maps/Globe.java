package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;

public class Globe extends EntityMap{
    public Globe(Config config, EntitiesContainer entitiesContainer) {
        super(config, entitiesContainer);
    }

    @Override
    protected void bringBackToBounds(Animal animal) {
        int newX = animal.getPosition().x;
        int newY = animal.getPosition().y;
        if (animal.getPosition().x < 0) {
            newX = size.x - 1;
        } else if (animal.getPosition().x > size.x - 1) {
            newX = 0;
        }

        if (animal.getPosition().y < 0) {
            newY = 0;
            animal.rotateBy(4);
        } else if (animal.getPosition().y > size.y - 1) {
            newY = size.y - 1;
            animal.rotateBy(4);
        }
        animal.moveTo(new Vector(newX, newY));
    }
}
