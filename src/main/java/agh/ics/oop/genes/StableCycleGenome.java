package agh.ics.oop.genes;

import java.util.List;

public class StableCycleGenome extends Genome{

    public StableCycleGenome(int genomeLength) {
        super(genomeLength);
    }

    public StableCycleGenome(List<Integer> genes) {
        super(genes);
    }

    @Override
    public Integer getNextGene() {
        currentGeneIndex = (currentGeneIndex + 1) % genes.size();
        return genes.get(currentGeneIndex);
    }

    @Override
    public boolean equals(Object other)  {
        if (this == other) return true;
        if (!(other instanceof StableCycleGenome other_g)) return false;
        return this.genes.equals(other_g.genes);
    }
}
