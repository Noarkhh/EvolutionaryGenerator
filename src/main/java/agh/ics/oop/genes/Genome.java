package agh.ics.oop.genes;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;

import java.util.Arrays;
import java.util.Random;

public abstract class Genome {
    protected final int[] genes;
    public final int length;
    protected int currentGeneIndex;
    protected final Random RNG = new Random();

    protected Genome(int genomeLength) {
        genes = new int[genomeLength];
        length = genomeLength;
        for (int i = 0; i < genomeLength; i++) {
            genes[i] = RNG.nextInt(7);
        }
        currentGeneIndex = RNG.nextInt(genomeLength);
    }

    protected Genome(int[] genes) {
        this.genes = genes;
        length = genes.length;
        currentGeneIndex = RNG.nextInt(genes.length);
    }

    public abstract int getNextGene();

    public int getGeneAt(int index) {
        return genes[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(genes);
    }
}
