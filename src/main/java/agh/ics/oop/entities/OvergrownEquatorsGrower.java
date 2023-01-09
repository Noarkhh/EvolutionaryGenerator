package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;

import java.util.HashMap;

public class OvergrownEquatorsGrower extends PlantGrower {
    private final int equatorTop;
    private final int equatorBottom;
    private final Config config;

    public OvergrownEquatorsGrower(Config config, HashMap<Vector, Plant> plants) {
        super(plants);
        this.config = config;
        equatorTop = (int) (config.getMapSize().y * 0.4);
        equatorBottom = (int) (config.getMapSize().y * 0.6);
    }

    @Override
    public Vector getNewPlantPosition() {
        Vector newPosition;
        boolean generateOnEquator = RNG.nextInt(5) != 0;
        int attempts = 10;
        do {
            int x = RNG.nextInt(config.getMapSize().x);
            int y;
            if (!generateOnEquator) {
                if (RNG.nextInt(2) == 0) y = RNG.nextInt(equatorTop);
                else y = RNG.nextInt(equatorBottom, config.getMapSize().y);
            } else y = RNG.nextInt(equatorTop, equatorBottom);
            newPosition = new Vector(x, y);
            attempts--;
        } while (plants.containsKey(newPosition) && attempts >= 0);
        return newPosition;
    }
}
