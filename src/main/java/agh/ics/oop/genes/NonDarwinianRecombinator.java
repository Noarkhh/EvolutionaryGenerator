package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

public class NonDarwinianRecombinator implements GeneRecombinator {


    @Override
    public int[] recombine(Animal animal1, Animal animal2) {
        return new int[0];
    }
}
