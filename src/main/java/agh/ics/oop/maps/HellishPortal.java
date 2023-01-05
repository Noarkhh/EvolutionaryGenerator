package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;

import java.util.Random;

public class HellishPortal extends EntityMap {
    public HellishPortal(Config config, EntitiesContainer entitiesContainer) {
        super(config, entitiesContainer);
    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {
        Random RNG = new Random();
        movedAnimal.moveTo(new Vector(RNG.nextInt(size.x), RNG.nextInt(size.y)));
        movedAnimal.spendProcreationEnergy();
    }
}
