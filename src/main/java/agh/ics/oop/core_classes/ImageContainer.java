package agh.ics.oop.core_classes;

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
        return null;
    }
}
