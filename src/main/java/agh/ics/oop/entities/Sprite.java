package agh.ics.oop.entities;

import agh.ics.oop.core_classes.ImageContainer;
import agh.ics.oop.core_classes.Vector;
import javafx.scene.image.Image;

public abstract class Sprite {
    protected Vector position;
    protected Image image;
    public static ImageContainer imageContainer;

    protected Sprite(Vector position) {
        this.position = position;
        this.image = imageContainer.getImage(this);
    }

    public Vector getPosition() {
        return position;
    }

    public Image getImage() {
        return image;
    }
}
