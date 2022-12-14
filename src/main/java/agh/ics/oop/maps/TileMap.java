package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Tile;
import agh.ics.oop.core_classes.Vector;

import java.util.List;

public class TileMap implements IWorldMap<Tile> {
    private final Tile[][] tiles;

    private final Config config;
    private final Vector size;

    public TileMap(Config config) {
        this.config = config;
        size = config.getMapSize();
        tiles = new Tile[size.x][size.y];
    }

    @Override
    public List<Tile> objectsAt(Vector position) {
        return null;
    }

    @Override
    public void place(Tile objectToPlace) {

    }

    @Override
    public boolean remove(Vector position, Tile objectToRemove) {
        return false;
    }

    @Override
    public boolean remove(Tile objectToRemove) {
        return false;
    }
}
