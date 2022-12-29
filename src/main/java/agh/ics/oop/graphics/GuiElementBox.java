package agh.ics.oop.graphics;

import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.Entity;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {
    private final VBox vBox;

    public GuiElementBox(Entity entity) {
        ImageView entityImageView = new ImageView(entity.getImage());
        if (entity instanceof Animal) {
            entityImageView.setFitHeight(45);
            entityImageView.setFitWidth(45);
            vBox = new VBox(entityImageView, new Label(((Integer) ((Animal) entity).getEnergy()).toString()));
        } else {
            entityImageView.setFitHeight(60);
            entityImageView.setFitWidth(60);
            vBox = new VBox(entityImageView);
        }
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getBox() { return vBox; }
}
