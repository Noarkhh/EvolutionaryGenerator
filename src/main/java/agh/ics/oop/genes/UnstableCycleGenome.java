package agh.ics.oop.genes;

import java.util.List;

public class UnstableCycleGenome extends Genome {

    protected UnstableCycleGenome(int genomeLength) {
        super(genomeLength);
    }

    protected UnstableCycleGenome(List<Integer> genes) {
        super(genes);
    }

    @Override
    public Integer getNextGene() {
        if (RNG.nextInt(5) == 0) {
            currentGeneIndex = RNG.nextInt(length);
        } else {
            currentGeneIndex = (currentGeneIndex + 1) % 8;
        }
        return genes.get(currentGeneIndex);
    }

    @Override
    public boolean equals(Object other)  {
        if (this == other) return true;
        if (!(other instanceof UnstableCycleGenome other_g)) return false;
        return this.genes.equals(other_g.genes);
    }
}
