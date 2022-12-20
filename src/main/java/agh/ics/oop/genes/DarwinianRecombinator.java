package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

public class DarwinianRecombinator extends GeneRecombinator {

    @Override
    protected float getLeftGenomeRatio(Animal animal1, Animal animal2) {
        return 0.5F;
    }
}
