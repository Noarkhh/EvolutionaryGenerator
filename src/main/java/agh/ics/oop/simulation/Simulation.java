package agh.ics.oop.simulation;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.entities.EntitiesEngine;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.graphics.GuiElementBox;
import agh.ics.oop.maps.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private final Statistics statistics;

    private final Vector windowSize = new Vector(1400, 1000);
    private final Vector verticalHeaderCellSize = new Vector(25, 60);
    private final Vector horizontalHeaderCellSize = new Vector(60, 25);

    private final GridPane grid = new GridPane();

    public Simulation(String configPath) throws IOException, ParseException {
        Config config = new Config(configPath);
        EntitiesContainer entitiesContainer = new EntitiesContainer();
        entityMap = MapFactory.createEntityMap(config, entitiesContainer);
        entitiesEngine = new EntitiesEngine(config, this, entitiesContainer, entityMap);
        statistics = new Statistics(config, entitiesContainer);

        drawHeaders();
        updateGrid();
        setupWindow();

        entitiesEngine.start();
    }

    private void setupWindow() {

        Stage stage = new Stage();
        HBox mainBox = new HBox(grid);

        Button unpauseButton = new Button("Unpause");
        unpauseButton.setOnAction(actionEvent -> entitiesEngine.getMutex().unlock());
        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(actionEvent -> entitiesEngine.getMutex().lock());
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(actionEvent -> {
            entitiesEngine.stopSimulation();
            stage.close();
        });


        VBox vBox = new VBox(unpauseButton, pauseButton, quitButton, statistics.getGrid());

        mainBox.getChildren().add(vBox);
        Scene scene = new Scene(mainBox, windowSize.x, windowSize.y);
        stage.setScene(scene);
        stage.show();
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
                GuiElementBox guiElementBox = new GuiElementBox(entity);
                grid.add(guiElementBox.getBox(), entitiesEntry.getKey().x + 1, entitiesEntry.getKey().y + 1);
                GridPane.setHalignment(guiElementBox.getBox(), HPos.CENTER);
            }
        }
    }

    public void update() {
        Platform.runLater(this::updateGrid);
    }

    public void refreshStatistics() {
        Platform.runLater(statistics::refresh);
    }
}
