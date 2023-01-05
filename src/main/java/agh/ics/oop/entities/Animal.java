package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.MapDirection;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.genes.Genome;
import agh.ics.oop.genes.GenomeFactory;
import agh.ics.oop.maps.EntityMap;

import java.util.LinkedList;
import java.util.List;

public class Animal extends Entity {

    private final Config config;
    private MapDirection direction = MapDirection.getRandom();
    private Vector previousPosition;
    private int energy;
    private int age = 0;
    private boolean isAlive = true;
    private final List<Animal> children = new LinkedList<>();
    private final List<Animal> parents;
    public final Genome genome;

    public Animal(Vector position, EntityMap entityMap, Config config, Genome genome, List<Animal> parents) {
        super(position, entityMap);
        previousPosition = position;
        this.config = config;
        this.genome = genome;
        this.parents = parents;
        energy = parents.size() > 0 ? config.getProcreationEnergyCost() * parents.size() : config.getStartingAnimalEnergy();
        image = imageContainer.getImage(this);
    }

    public MapDirection getDirection() {
        return direction;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge() {
        return age;
    }

    public List<Animal> getChildren() {
        return children;
    }

    public void rotateBy(int rotation) {
        direction = direction.rotateBy(rotation);
        image = imageContainer.getImage(this);
    }

    public void rotate() {
        rotateBy(genome.getNextGene());
    }

    public void move() {
        previousPosition = position;
        position = position.add(direction.toUnitVector());
        age++;
        positionChanged();
    }

    public void moveTo(Vector newPosition) {
        previousPosition = position;
        position = newPosition;
        positionChanged();
    }

    private void positionChanged() {
        for (IPositionObserver observer : observers) observer.positionChanged(this, previousPosition, position);
    }

    public void eatPlant(Plant plant) {
        energy += plant.getEnergy();
    }

    public void spendProcreationEnergy() {
        energy -= config.getProcreationEnergyCost();
    }

    public boolean isAbleToProcreate() {
        return energy >= config.getProcreationEnergyCost();
    }

    public void addChild(Animal child) {
        children.add(child);
    }

    public Animal procreate(Animal partner) {
        Animal child = new Animal(position, entityMap, config,
                GenomeFactory.createGenome(config, this, partner), new LinkedList<>(List.of(this, partner)));
        spendProcreationEnergy();
        addChild(child);
        partner.spendProcreationEnergy();
        partner.addChild(child);

        return child;
    }

    public void spendLivingEnergy() {
        energy--;
        if (energy < 0) {
            isAlive = false;
            remove();
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public String toString() {
        return "Animal(e: " + energy + ", " + position + ")";
    }
}
