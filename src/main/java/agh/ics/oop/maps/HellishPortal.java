package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Tile;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;

public class HellishPortal extends EntityMap{
    public HellishPortal(Config config, EntitiesContainer entitiesContainer, TileMap tileMap) {
        super(config, entitiesContainer, tileMap);
    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {

    }
}
