package agh.ics.oop.genes;

import agh.ics.oop.config.Config;

import java.util.Random;

public class RandomMutator implements Mutator {
    private final int minMutations;
    private final int maxMutations;
    private final Random RNG = new Random();

    public RandomMutator(Config config) {
        minMutations = config.getMinMutations();
        maxMutations = config.getMaxMutations();
    }

    @Override
    public int[] mutate(int[] genes) {
        int mutations = RNG.nextInt(minMutations, maxMutations + 1);
        for (int i = 0; i < mutations; i++) {
            genes[RNG.nextInt(genes.length)] = RNG.nextInt(7);
        }
        return genes;
    }
}
