package agh.ics.oop.simulation;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.entities.EntitiesEngine;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.graphics.GuiElementBox;
import agh.ics.oop.maps.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Simulation {

    private final EntityMap entityMap;
    private final EntitiesEngine entitiesEngine;
    private final SimulationStatistics simulationStatistics;
    private AnimalStatistics animalStatistics;

    private final CSVSaver csvSaver;

    private final Vector windowSize = new Vector(1400, 1000);
    private final Vector verticalHeaderCellSize = new Vector(25, 60);
    private final Vector horizontalHeaderCellSize = new Vector(60, 25);

    private final GridPane grid = new GridPane();


    public Simulation(String configPath, boolean saveToCSV) throws IOException, ParseException {
        Config config = new Config(configPath);
        EntitiesContainer entitiesContainer = new EntitiesContainer();
        entityMap = MapFactory.createEntityMap(config, entitiesContainer);
        entitiesEngine = new EntitiesEngine(config, this, entitiesContainer, entityMap);
        simulationStatistics = new SimulationStatistics(config, entitiesContainer);
        if (saveToCSV) csvSaver = new CSVSaver("src/main/resources/logs", simulationStatistics);
        else csvSaver = null;

        grid.setOnMouseClicked(event -> {
            if (entitiesEngine.getMutex().isLocked()) {
                animalStatistics.updateSelectedAnimal(selectAnimal(event));
                animalStatistics.refresh();
                updateGrid();
            }
        });

        drawHeaders();
        setupWindow();
        updateGrid();

        entitiesEngine.start();
    }

    private void setupWindow() {

        Stage stage = new Stage();

        Button unpauseButton = new Button("Unpause");
        Button pauseButton = new Button("Pause");
        unpauseButton.setOnAction(actionEvent -> {
            entitiesEngine.getMutex().unlock();
            unpauseButton.setDisable(true);
            pauseButton.setDisable(false);
        });
        unpauseButton.setPrefSize(100, 30);
        unpauseButton.setDisable(true);

        pauseButton.setOnAction(actionEvent -> {
            entitiesEngine.getMutex().lock();
            unpauseButton.setDisable(false);
            pauseButton.setDisable(true);
        });
        pauseButton.setPrefSize(100, 30);

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(actionEvent -> {
            entitiesEngine.stopSimulation();
            stage.close();
        });
        quitButton.setPrefSize(100, 30);


        VBox infoBox = new VBox(pauseButton, unpauseButton, quitButton, simulationStatistics.getGrid());
        infoBox.setAlignment(Pos.TOP_CENTER);
        infoBox.setSpacing(10);

        animalStatistics = new AnimalStatistics(infoBox);

        HBox mainBox = new HBox(grid, infoBox);
        mainBox.setSpacing(10);
        Scene scene = new Scene(mainBox, windowSize.x, windowSize.y);
        stage.setScene(scene);
        stage.show();
    }

    private Animal selectAnimal(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode().getParent();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        if (colIndex == null) return null;
        return (Animal) entityMap.objectsAt(new Vector(colIndex - 1, rowIndex - 1)).stream().filter(en -> en instanceof Animal).toList().get(0);
    }

    private void updateGrid() {
        removeEntities();
        grid.setGridLinesVisible(false);
        drawEntities();
        grid.setGridLinesVisible(true);
    }

    private void drawHeaders() {
        Label xy = new Label("x\\y");
        GridPane.setHalignment(xy, HPos.CENTER);
        grid.add(xy, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(verticalHeaderCellSize.x));
        grid.getRowConstraints().add(new RowConstraints(horizontalHeaderCellSize.y));

        for (int i = 0; i < entityMap.size.x; i++) {
            Label label = new Label(String.format("%2d", i));
            grid.add(label, i + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(horizontalHeaderCellSize.x));
        }

        for (int i = 0; i < entityMap.size.y; i++) {
            Label label = new Label(String.format("%2d", i));
            grid.add(label, 0, i + 1);
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(verticalHeaderCellSize.y));
        }

    }

    private void removeEntities() {
        int gridHeaderChildren = entityMap.size.x + entityMap.size.y + 1;
        grid.getChildren().subList(gridHeaderChildren, grid.getChildren().size()).clear();
    }

    private void drawEntities() {
        for (Map.Entry<Vector, List<Entity>> entitiesEntry : new HashSet<>(entityMap.getEntities().entrySet())) {
            for (Entity entity : entitiesEntry.getValue()) {
                GuiElementBox guiElementBox = new GuiElementBox(entity, simulationStatistics, animalStatistics.getSelectedAnimal());
                grid.add(guiElementBox.getBox(), entitiesEntry.getKey().x + 1, entitiesEntry.getKey().y + 1);
                GridPane.setHalignment(guiElementBox.getBox(), HPos.CENTER);
            }
        }
    }

    public void update() {
        Platform.runLater(() -> {
                updateGrid();
                animalStatistics.refresh();
        });
    }

    public void refreshStatistics() {
        Platform.runLater(() -> {
            simulationStatistics.refresh();
            if (csvSaver != null) csvSaver.logCurrentDay();
        });
    }
}
