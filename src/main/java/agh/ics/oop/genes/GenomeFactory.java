package agh.ics.oop.genes;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.Animal;

import java.util.List;

public class GenomeFactory {
    public static Genome createGenome(Config config, Animal parent1, Animal parent2) {
        Mutator mutator = switch (config.getMutationsVariant()) {
            case FULL_RANDOM -> new RandomMutator(config);
            case SLIGHT_CORRECTION -> new CorrectingMutator(config);
        };

        GeneRecombinator geneRecombinator = switch (config.getRecombinationVariant()) {
            case DARWINIAN -> new DarwinianRecombinator();
            case NON_DARWINIAN -> new NonDarwinianRecombinator();
        };

        List<Integer> genes = mutator.mutate(geneRecombinator.recombine(parent1, parent2));

        return switch (config.getBehaviorVariant()) {
            case A_TAD_OF_TOMFOOLERY -> new UnstableCycleGenome(genes);
            case FULL_PREDESTINATION -> new StableCycleGenome(genes);
        };
    }

    public static Genome createGenome(Config config) {
        return switch (config.getBehaviorVariant()) {
            case A_TAD_OF_TOMFOOLERY -> new UnstableCycleGenome(config.getGenomeLength());
            case FULL_PREDESTINATION -> new StableCycleGenome(config.getGenomeLength());
        };
    }
}
