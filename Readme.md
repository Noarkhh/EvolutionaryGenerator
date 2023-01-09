# Evolutionary Generator

Project based on given instructions and requirements:
 - https://github.com/apohllo/obiektowe-lab/tree/master/proj1

## Creating custom configuration

Simply create a new .json file in `config` directory with the given structure:

```
{
  "option 1": option1value,
  "option 2": option2value,
  ...
}
```

### Configuration options
  - mapWidth - width of the map in cells
  - mapHeight - height of the map in cells
  - cellSize - size of a single cell in pixels
  - mapVariant - available options:
    - Globe
    - Hellish Portal
  - startingPlants - amount of starting plants
  - plantsEnergy - energy supplied by eating a single plant
  - dailyPlants - amount of new plants growing every day
  - plantGrowthVariant - available options:
    - Overgrown Equators
    - Toxic Carcasses
  - startingAnimalEnergy - starting energy of first generation of animals 
  - satiatedEnergy - energy needed for an animal to procreate
  - procreationEnergyCost - energy spent for bringing a new child into this world
  - minMutations - minimum amount of mutations of a new animal
  - maxMutations - maximum amount of mutations of a new animal
  - recombinationVariant - available options:

    *(In Darwinian Recombination the animal gets the same amount of genes from both parents, whereas in Non-Darwinian
    the share of genes inherited from each parent are proportionate to their respective energy levels)*
    - Darwinian
    - Non-Darwinian
  - mutationsVariant - available options:
    - Full Random
    - Slight Correction
  - genomeLength - length of the genome       
  - behaviorVariant - available options:
    - Full Predestination
    - A Tad Of Tomfoolery
  - millisecondsBetweenStages - time between stages in simulation

## CSV logging

If selected before running the simulation every-day statistics will be saved in a file in `logs` directory.

## Animal highlighting

The animals with the most popular genome have their energy level **bolded** and *italicized*, where the currently 
selected animal has its energy level **bolded** and surrounded with `!*` and `*!`

