package agh.ics.oop.entities;

import agh.ics.oop.core_classes.Vector;

import java.util.*;
import java.util.Map.Entry;

public class CompetitorsGenerator {

    private final Iterator<Entry<Vector, List<Entity>>> entityIterator;
    private final List<Animal> currentCompetitors = new LinkedList<>();
    private Vector currentPosition;

    public CompetitorsGenerator(HashMap<Vector, List<Entity>> entities) {
//        this.entities = new LinkedList<>(entities.entrySet());
        entityIterator = new LinkedList<>(entities.entrySet()).iterator();
        next(true);
    }

    public Vector getCurrentPosition() {
        return currentPosition;
    }

    public List<Animal> next(boolean nextTile) {

        if (nextTile) {
            if (!entityIterator.hasNext()) return null;
            Entry<Vector, List<Entity>> currentEntry;
            try {
                currentEntry = entityIterator.next();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
            currentPosition = currentEntry.getKey();

            currentCompetitors.clear();
            for (Entity entity : currentEntry.getValue()) {
                if (entity instanceof Animal) currentCompetitors.add((Animal) entity);
            }
        }
        currentCompetitors.sort(new AnimalComparator());
        return currentCompetitors;
    }
}
