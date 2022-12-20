package agh.ics.oop.genes;

import agh.ics.oop.entities.Animal;

public class StableCycleGenome extends Genome{
    protected StableCycleGenome(int genomeLength) {
        super(genomeLength);
    }

    protected StableCycleGenome(int genomeLength, Animal parent1, Animal parent2, Mutator mutator, GeneRecombinator geneRecombinator) {
        super(genomeLength, parent1, parent2, mutator, geneRecombinator);
    }

    @Override
    public int getGene() {
        return 0;
    }
}
