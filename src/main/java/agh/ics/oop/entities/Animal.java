package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.MapDirection;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.genes.Genome;
import agh.ics.oop.genes.GenomeFactory;
import agh.ics.oop.maps.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class Animal extends Entity {

    private Config config;
    private MapDirection direction = MapDirection.getRandom();
    private int energy;
    private int age = 0;
    private final List<Animal> children = new LinkedList<>();
    public final Genome genome;

    public Animal(Vector position, EntityMap entityMap, Config config, Genome genome, int energy) {
        super(position, entityMap);
        this.config = config;
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

    public void addChild(Animal child) {
        children.add(child);
    }

    public void spendProcreationEnergy() {
        energy -= config.getProcreationEnergyCost();
    }

    public Animal procreate(Animal partner) {
        Animal child = new Animal(position, entityMap, config,
                GenomeFactory.createGenome(config, this, partner), config.getProcreationEnergyCost() * 2);
        spendProcreationEnergy();
        addChild(child);
        partner.spendProcreationEnergy();
        partner.addChild(child);

        return child;
    }
}
