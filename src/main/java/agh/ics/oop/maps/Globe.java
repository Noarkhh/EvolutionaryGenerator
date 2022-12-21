package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Tile;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.PlantGrower;

public class Globe extends EntityMap{
    public Globe(Config config, TileMap tileMap) {
        super(config, tileMap);
    }

    @Override
    protected void bringBackToBounds(Animal movedAnimal) {

    }
}
