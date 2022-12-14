package agh.ics.oop.core_classes;

import agh.ics.oop.entities.Sprite;

public class Tile extends Sprite {
    public final Terrain terrain;
    private int deaths = 0;

    public Tile(Vector position, Terrain terrain) {
        super(position);
        this.terrain = terrain;
    }
}
