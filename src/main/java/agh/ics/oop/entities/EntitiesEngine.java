package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.maps.EntityMap;

import java.util.*;

public class EntitiesEngine {
    private final HashMap<Vector, List<Entity>> entities;
    private final EntityMap entityMap;

    public EntitiesEngine (HashMap<Vector, List<Entity>> entities, EntityMap entityMap) {
        this.entities = entities;
        this.entityMap = entityMap;
    }

    public void procreate() {
        CompetitorsGenerator generator = new CompetitorsGenerator(entities);
        List<Animal> competitors = generator.next(false);
        while (competitors != null) {
            if (competitors.size() < 2) {
                competitors = generator.next(true);
                continue;
            }
            Animal parent1 = competitors.get(0), parent2 = competitors.get(1);
            if (!parent1.isAbleToProcreate() || !parent2.isAbleToProcreate()) {
                competitors = generator.next(true);
                continue;
            }
            entityMap.place(parent1.procreate(parent2));
            System.out.println(parent1 + " and " + parent2 + " had a child!");
            generator.next(false);
        }
    }

    public void feast() {
        CompetitorsGenerator generator = new CompetitorsGenerator(entities);
        List<Animal> competitors = generator.next(false);
        List<Plant> plants = getPlantsFrom(generator.getCurrentPosition());

        while (competitors != null) {
            if (plants.size() == 0) {
                competitors = generator.next(true);
                plants = getPlantsFrom(generator.getCurrentPosition());
                continue;
            }
            if (competitors.size() == 0) {
                competitors = generator.next(true);
                plants = getPlantsFrom(generator.getCurrentPosition());
                continue;
            }
            competitors.get(0).eatPlant(plants.get(0));
            System.out.println(plants.get(0).toString() + " eaten!");
            entityMap.remove(plants.get(0));
            plants.remove(0);
            generator.next(false);
        }
    }

    private List<Plant> getPlantsFrom(Vector position) {
        List<Plant> plants = new LinkedList<>();
        for (Entity entity : entities.get(position)) {
            if (entity instanceof Plant) plants.add((Plant) entity);
        }
        plants.sort(Comparator.comparingInt(Plant::getEnergy));
        return plants;
    }
}
