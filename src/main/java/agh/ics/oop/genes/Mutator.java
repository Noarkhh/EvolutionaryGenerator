package agh.ics.oop.genes;

import agh.ics.oop.config.Config;

import java.util.Random;

public abstract class Mutator {
    protected final int minMutations;
    protected final int maxMutations;
    protected final Random RNG = new Random();

    protected Mutator(Config config) {
        minMutations = config.getMinMutations();
        maxMutations = config.getMaxMutations();
    }
    public abstract int[] mutate(int[] genes);
}
