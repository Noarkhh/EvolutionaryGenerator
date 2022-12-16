package agh.ics.oop.graphics;

import agh.ics.oop.core_classes.MapDirection;
import agh.ics.oop.core_classes.Tile;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.Plant;
import agh.ics.oop.entities.Sprite;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ImageContainer {
    private final Map<String, Image> imageMap = new HashMap<>();

    public ImageContainer(List<String> spritesToLoadNames) throws NullPointerException {
        Sprite.imageContainer = this;

        for (String spriteName : spritesToLoadNames) {
            imageMap.put(spriteName, new Image(Objects.requireNonNull(Image.class.getClassLoader().getResourceAsStream(spriteName + ".png"))));
        }
    }

    public Image getImage(Sprite sprite) {
        if (sprite instanceof Animal) {
            if (((Animal) sprite).getDirection() == null) return imageMap.get(MapDirection.N.toString());
            return imageMap.get(((Animal) sprite).getDirection().toString());
        } else if (sprite instanceof Plant) {
            return imageMap.get("plant");
        } else if (sprite instanceof Tile) {
            return imageMap.get(((Tile) sprite).terrain.toString());
        } else return imageMap.get("plant"); // placeholder
    }
}
