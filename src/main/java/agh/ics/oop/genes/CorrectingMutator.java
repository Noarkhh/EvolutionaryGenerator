package agh.ics.oop.genes;

import agh.ics.oop.config.Config;

import java.util.List;
import java.util.Random;

public class CorrectingMutator extends Mutator {
    public CorrectingMutator(Config config) {
        super(config);
    }

    @Override
    public List<Integer> mutate(List<Integer> genes) {
        int mutations = RNG.nextInt(minMutations, maxMutations + 1);
        for (int i = 0; i < mutations; i++) {
            int correction;
            if (RNG.nextInt(2) == 0) {
                correction = 1;
            } else {
                correction = -1;
            }
            int mutationIndex = RNG.nextInt(genes.size());
            genes.set(mutationIndex, (((genes.get(mutationIndex) + correction) % 8) + 8) % 8);
        }
        return genes;
    }
}
