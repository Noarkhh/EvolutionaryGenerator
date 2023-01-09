package agh.ics.oop.simulation;

import agh.ics.oop.entities.Animal;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AnimalStatistics implements Statistics {
    private Animal selectedAnimal = null;
    private final VBox box;
    private final GridPane grid;

    public AnimalStatistics(VBox box) {
        this.box = box;
        this.grid = new GridPane();
        setupGrid();
    }

    public void updateSelectedAnimal(Animal animal) {
        selectedAnimal = animal;
    }

    public Animal getSelectedAnimal() {
        return selectedAnimal;
    }

    @Override
    public void refresh() {
        if (selectedAnimal == null) {
            box.getChildren().remove(grid);
            return;
        }
        if (!box.getChildren().get(box.getChildren().size() - 1).equals(grid)) {
            box.getChildren().add(grid);
        }
        grid.getChildren().subList(7, grid.getChildren().size()).clear();
        grid.add(new Text(selectedAnimal.genome.toString()), 1, 0);
        grid.add(new Text(createGenePointer()), 1, 1);
        grid.add(new Text(Integer.toString(selectedAnimal.getEnergy())), 1, 2);
        grid.add(new Text(Integer.toString(selectedAnimal.getPlantsEaten())), 1, 3);
        grid.add(new Text(Integer.toString(selectedAnimal.getChildren().size())), 1, 4);
        grid.add(new Text(Integer.toString(selectedAnimal.getAge())), 1, 5);
    }

    private void setupGrid() {
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(200));
        grid.getColumnConstraints().add(new ColumnConstraints(200));
        for (int i = 0; i < 6; i++) grid.getRowConstraints().add(new RowConstraints(30));
        grid.add(new Text("Genome"), 0, 0);
        grid.add(new Text("Active gene"), 0, 1);
        grid.add(new Text("Energy"), 0, 2);
        grid.add(new Text("Plants eaten"), 0, 3);
        grid.add(new Text("Children"), 0, 4);
        grid.add(new Text("Age"), 0, 5);
    }

    private String createGenePointer() {
        return " " + "    ".repeat(selectedAnimal.genome.getCurrentGeneIndex()) + "^";
    }
}
