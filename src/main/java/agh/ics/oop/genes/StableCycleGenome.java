package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

public class StableCycleGenome extends Genome{

    protected StableCycleGenome(int genomeLength) {
        super(genomeLength);
    }

    protected StableCycleGenome(int[] genes) {
        super(genes);
    }

    @Override
    public int getNextGene() {
        currentGeneIndex = (currentGeneIndex + 1) % 8;
        return genes[currentGeneIndex];
    }
}
