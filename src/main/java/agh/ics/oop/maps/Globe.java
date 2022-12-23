package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Tile;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.entities.PlantGrower;

public class Globe extends EntityMap{
    public Globe(Config config, EntitiesContainer entitiesContainer, TileMap tileMap) {
        super(config, entitiesContainer, tileMap);
    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {

    }
}
