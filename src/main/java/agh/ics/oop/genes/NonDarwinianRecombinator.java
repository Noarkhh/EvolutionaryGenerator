package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

import java.util.Random;

public class NonDarwinianRecombinator extends GeneRecombinator {
    private Random RNG = new Random();

    @Override
    protected float getLeftGenomeRatio(Animal animal1, Animal animal2) {
        return (float) animal1.getEnergy() / (float) (animal1.getEnergy() + animal2.getEnergy());
    }
}
