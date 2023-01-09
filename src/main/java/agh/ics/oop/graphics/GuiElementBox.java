package agh.ics.oop.graphics;

import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.simulation.SimulationStatistics;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GuiElementBox {
    private final VBox vBox;

    public GuiElementBox(Entity entity, SimulationStatistics statistics, Animal selectedAnimal, int cellSize) {
        ImageView entityImageView = new ImageView(entity.getImage());
        if (entity instanceof Animal animal) {
            entityImageView.setFitHeight(cellSize * 0.75);
            entityImageView.setFitWidth(cellSize * 0.75);
            Text text;
            if (entity.equals(selectedAnimal)) {
                text = new Text("!*" + animal.getEnergy() + "*!");
                text.setFont(Font.font("", FontWeight.BOLD, cellSize * 0.2));
            } else {
                text = new Text(Integer.toString(animal.getEnergy()));
                text.setFont(Font.font(cellSize * 0.2));
            }
            if (animal.genome.equals(statistics.getMostPopularGenome())) {
                text.setFont(Font.font("", FontWeight.BOLD, FontPosture.ITALIC, cellSize * 0.2));
            }
            vBox = new VBox(entityImageView, text);

        } else {
            entityImageView.setFitHeight(cellSize * 0.9);
            entityImageView.setFitWidth(cellSize * 0.9);
            vBox = new VBox(entityImageView);
        }
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getBox() { return vBox; }
}
