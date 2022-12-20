package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

import java.util.List;

public interface GeneRecombinator {
    int[] recombine(Animal animal1, Animal animal2);
}
