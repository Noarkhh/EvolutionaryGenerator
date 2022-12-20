package agh.ics.oop.genes;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;

import java.util.Random;

public abstract class Genome {
    private final int[] genes;
    private int currentGeneIndex;
    private final Random RNG = new Random();

    protected Genome(int genomeLength) {
        genes = new int[genomeLength];
        for (int i = 0; i < genomeLength; i++) {
            genes[i] = RNG.nextInt(7);
        }
        currentGeneIndex = RNG.nextInt(genomeLength);
    }

    protected Genome(int genomeLength, Animal parent1, Animal parent2, Mutator mutator, GeneRecombinator recombinator) {
        genes =  mutator.mutate(recombinator.recombine(parent1, parent2));
        currentGeneIndex = RNG.nextInt(genomeLength);
    }

    public abstract int getGene();
}
