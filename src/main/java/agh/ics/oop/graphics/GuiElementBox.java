package agh.ics.oop.graphics;

import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.simulation.Statistics;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GuiElementBox {
    private final VBox vBox;

    public GuiElementBox(Entity entity, Statistics statistics) {
        ImageView entityImageView = new ImageView(entity.getImage());
        if (entity instanceof Animal animal) {
            entityImageView.setFitHeight(45);
            entityImageView.setFitWidth(45);
            Text text = new Text(Integer.toString(animal.getEnergy()));
            if (animal.genome.equals(statistics.getMostPopularGenome())) {
                text.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, 12));
            }
            vBox = new VBox(entityImageView, text);

        } else {
            entityImageView.setFitHeight(60);
            entityImageView.setFitWidth(60);
            vBox = new VBox(entityImageView);
        }
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getBox() { return vBox; }
}
