package agh.ics.oop.maps;

import agh.ics.oop.config.Config;
import agh.ics.oop.entities.EntitiesContainer;

public class MapFactory {
    public static EntityMap createEntityMap(Config config, EntitiesContainer entitiesContainer) {
        return switch (config.getMapVariant()) {
            case GLOBE -> new Globe(config, entitiesContainer);
            case HELLISH_PORTAL -> new HellishPortal(config, entitiesContainer);
        };

    }
}
