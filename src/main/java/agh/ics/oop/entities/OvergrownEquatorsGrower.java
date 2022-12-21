package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.TileMap;

import java.util.Random;

public class OvergrownEquatorsGrower implements PlantGrower {
    private final TileMap tileMap;
    private final Random RNG = new Random();
    private int equatorTop;
    private int equatorBottom;

    public OvergrownEquatorsGrower(TileMap tileMap) {
        this.tileMap = tileMap;
        equatorTop = (int) (tileMap.getSize().y * 0.4);
        equatorBottom = (int) (tileMap.getSize().y * 0.6);
    }
    @Override
    public Vector getNewPlantPosition() {
        int x = RNG.nextInt(tileMap.getSize().x);
        int y;
        if (RNG.nextInt(5) == 0) {
            if (RNG.nextInt(2) == 0) y = RNG.nextInt(equatorTop);
            else y = RNG.nextInt(equatorBottom, tileMap.getSize().y);
        } else y = RNG.nextInt(equatorTop, equatorBottom);

        return new Vector(x, y);
    }
}
