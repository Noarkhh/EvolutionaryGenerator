package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

public class UnstableCycleGenome extends Genome {
    protected UnstableCycleGenome(int genomeLength) {
        super(genomeLength);
    }

    protected UnstableCycleGenome(int genomeLength, Animal parent1, Animal parent2, Mutator mutator, GeneRecombinator geneRecombinator) {
        super(genomeLength, parent1, parent2, mutator, geneRecombinator);
    }

    @Override
    public int getGene() {
        return 0;
    }
}
