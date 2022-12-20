package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

public class UnstableCycleGenome extends Genome {

    protected UnstableCycleGenome(int genomeLength) {
        super(genomeLength);
    }

    protected UnstableCycleGenome(int[] genes) {
        super(genes);
    }

    @Override
    public int getNextGene() {
        if (RNG.nextInt(5) == 0) {
            currentGeneIndex = RNG.nextInt(length);
        } else {
            currentGeneIndex = (currentGeneIndex + 1) % 8;
        }
        return genes[currentGeneIndex];
    }
}
