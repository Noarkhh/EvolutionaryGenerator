package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Tile;
import agh.ics.oop.entities.Animal;

public class HellishPortal extends EntityMap{
    public HellishPortal(Config config, TileMap tileMap) {
        super(config, tileMap);
    }

    @Override
    public void growPlant() {

    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {

    }
}
