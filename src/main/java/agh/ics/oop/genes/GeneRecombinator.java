package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class GeneRecombinator {
    private Random RNG = new Random();

    public List<Integer> recombine(Animal animal1, Animal animal2) {
        Animal right, left;
        int genomeLength = animal1.genome.length;
        List<Integer> genes = new ArrayList<>();

        if (RNG.nextInt(2) == 0) {
            right = animal1;
            left = animal2;
        } else {
            right = animal2;
            left = animal1;
        }
        int cutoffPoint = (int) (genomeLength * getLeftGenomeRatio(left, right));
        for (int i = 0; i < cutoffPoint; i++) {
            genes.add(left.genome.getGeneAt(i));
        }
        for (int i = cutoffPoint; i < genomeLength; i++) {
            genes.add(right.genome.getGeneAt(i));
        }
        return genes;
    }

    protected abstract float getLeftGenomeRatio(Animal animal1, Animal animal2);
}
