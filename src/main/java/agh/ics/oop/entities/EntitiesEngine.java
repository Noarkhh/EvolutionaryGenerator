package agh.ics.oop.entities;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.genes.GenomeFactory;
import agh.ics.oop.simulation.Mutex;
import agh.ics.oop.simulation.Simulation;
import agh.ics.oop.maps.EntityMap;

import java.util.*;

public class EntitiesEngine extends Thread {
    private final Mutex mutex;
    private final Config config;
    private final Simulation app;
    private final HashMap<Vector, List<Entity>> entities;
    private final List<Animal> animals;

    private final EntityMap entityMap;
    private final PlantGrower plantGrower;

    private boolean running = true;
    private final long millisecondsBetweenStages;


    public EntitiesEngine(Config config, Simulation app, EntitiesContainer entitiesContainer, EntityMap entityMap) {
        this.mutex = new Mutex(false);
        this.config = config;
        this.app = app;
        this.entities = entitiesContainer.getEntities();
        this.animals = entitiesContainer.getAnimals();
        this.entityMap = entityMap;
        plantGrower = PlantGrowerFactory.createPlantGrower(config, entitiesContainer.getPlants());
        millisecondsBetweenStages = config.getMillisecondsBetweenStages();

        for (int i = 0; i < config.getStartingPlants(); i++) growPlant();
        for (int i = 0; i < config.getStartingAnimals(); i++) spawnAnimal();
    }

    @Override
    public void run() {
        while (running) {
            mutex.step();
            finishStage();
            purge();
            finishStage();
            rotate();
            finishStage();
            move();
            finishStage();
            feast();
            finishStage();
            procreate();
            finishStage();
            grow();
            app.refreshStatistics();
        }
    }

    private void finishStage() {
        try {
            app.update();
            Thread.sleep(millisecondsBetweenStages);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Mutex getMutex() {
        return this.mutex;
    }

    public void stopSimulation() {
        running = false;
    }

    private void purge() {
        for (Animal animal : new LinkedList<>(animals)) animal.spendLivingEnergy();
    }

    private void rotate() {
        for (Animal animal : animals) animal.rotate();

    }

    private void move() {
        for (Animal animal : animals) animal.move();
    }

    private void procreate() {
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

    private void feast() {
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
            plants.get(0).remove();
            plants.remove(0);
            generator.next(false);
        }
    }

    private void grow() {
        for (int i = 0; i < config.getDailyPlants(); i++) growPlant();
    }

    private List<Plant> getPlantsFrom(Vector position) {
        List<Plant> plants = new LinkedList<>();
        for (Entity entity : entities.get(position)) {
            if (entity instanceof Plant) plants.add((Plant) entity);
        }
        plants.sort(Comparator.comparingInt(Plant::getEnergy));
        return plants;
    }

    private void growPlant() {
        entityMap.place(new Plant(plantGrower.getNewPlantPosition(), entityMap, config));
    }

    private void spawnAnimal() {
        Random random = new Random();
        entityMap.place(new Animal(new Vector(random.nextInt(entityMap.size.x), random.nextInt(entityMap.size.y)), entityMap,
                config, GenomeFactory.createGenome(config), new LinkedList<>()));
    }
}
