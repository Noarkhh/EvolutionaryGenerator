package agh.ics.oop.entities;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal animal1, Animal animal2) {
        if (animal1.getEnergy() != animal2.getEnergy()) return animal1.getEnergy() - animal2.getEnergy();
        if (animal1.getAge() != animal2.getAge()) return animal1.getAge() - animal2.getAge();
        if (animal1.getChildren().size() != animal2.getChildren().size()) return animal1.getChildren().size() - animal2.getChildren().size();
        return 0;
    }

}
