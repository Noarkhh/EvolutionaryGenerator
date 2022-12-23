package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.TileMap;

import java.util.HashMap;

public class OvergrownEquatorsGrower extends PlantGrower {
    private final int equatorTop;
    private final int equatorBottom;

    public OvergrownEquatorsGrower(TileMap tileMap, HashMap<Vector, Plant> plants) {
        super(tileMap, plants);
        equatorTop = (int) (tileMap.getSize().y * 0.4);
        equatorBottom = (int) (tileMap.getSize().y * 0.6);
    }

    @Override
    public Vector getNewPlantPosition() {
        Vector newPosition;
        do {
            int x = RNG.nextInt(tileMap.getSize().x);
            int y;
            if (RNG.nextInt(5) == 0) {
                if (RNG.nextInt(2) == 0) y = RNG.nextInt(equatorTop);
                else y = RNG.nextInt(equatorBottom, tileMap.getSize().y);
            } else y = RNG.nextInt(equatorTop, equatorBottom);
            newPosition = new Vector(x, y);
        } while (plants.containsKey(newPosition));
        return newPosition;
    }
}
