package agh.ics.oop.genes;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;

import java.util.*;

public abstract class Genome {
    protected final List<Integer> genes;
    public final int length;
    protected int currentGeneIndex;
    protected final Random RNG = new Random();

    protected Genome(int genomeLength) {
        genes = new ArrayList<Integer>();
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

    public abstract int getNextGene();

    public int getGeneAt(int index) {
        return genes.get(index);
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
