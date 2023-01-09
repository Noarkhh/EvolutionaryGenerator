package agh.ics.oop.genes;

import java.util.*;

public abstract class Genome {
    protected final List<Integer> genes;
    public final int length;
    protected int currentGeneIndex;
    protected final Random RNG = new Random();

    protected Genome(int genomeLength) {
        genes = new ArrayList<>();
        length = genomeLength;
        for (int i = 0; i < genomeLength; i++) {
            genes.add(RNG.nextInt(7));
        }
        currentGeneIndex = RNG.nextInt(genomeLength);
    }

    protected Genome(List<Integer> genes) {
        this.genes = genes;
        length = genes.size();
        currentGeneIndex = RNG.nextInt(genes.size());
    }

    public abstract Integer getNextGene();

    public Integer getGeneAt(int index) {
        return genes.get(index);
    }

    public Integer getCurrentGeneIndex() {
        return currentGeneIndex;
    }

    public List<Integer> getGenes() {
        return Collections.unmodifiableList(genes);
    }

    @Override
    public String toString() {
        return genes.toString();
    }

    @Override
    abstract public boolean equals(Object other);
}
