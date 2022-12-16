package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;

public class HellishPortal extends EntityMap{
    public HellishPortal(Config config) {
        super(config);
    }

    @Override
    public void growPlant() {

    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {

    }
}
