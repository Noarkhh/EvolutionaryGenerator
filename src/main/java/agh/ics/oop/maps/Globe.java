package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;

public class Globe extends EntityMap{
    public Globe(Config config) {
        super(config);
    }

    @Override
    public void growPlant() {

    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {

    }
}
