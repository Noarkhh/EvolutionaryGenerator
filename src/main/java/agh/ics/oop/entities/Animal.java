package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.MapDirection;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.genes.Genome;
import agh.ics.oop.maps.EntityMap;

public class Animal extends Entity {

    private MapDirection direction = MapDirection.getRandom();
    private int energy;
    public final Genome genome;

    public Animal(Vector position, EntityMap entityMap, Config config, Genome genome, int energy) {
        super(position, entityMap);
        this.genome = genome;
        this.energy = energy;
        image = imageContainer.getImage(this);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public int getEnergy() {
        return energy;
    }
}
