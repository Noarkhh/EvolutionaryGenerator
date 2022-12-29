package agh.ics.oop.graphics;

import agh.ics.oop.config.Config;
import agh.ics.oop.config.ConfigSelector;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.entities.EntitiesEngine;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.maps.EntityMap;
import agh.ics.oop.maps.Globe;
import agh.ics.oop.maps.HellishPortal;
import agh.ics.oop.maps.TileMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Simulation {

    private final EntityMap entityMap;
    private TileMap tileMap;

    private final Vector windowSize = new Vector(1400, 1000);
    private final Vector verticalHeaderCellSize = new Vector(25, 60);
    private final Vector horizontalHeaderCellSize = new Vector(60, 25);

    private final GridPane grid = new GridPane();

    public Simulation(String configPath) throws Exception {
        Stage stage = new Stage();
        Config config = new Config(configPath);
        EntitiesContainer entitiesContainer = new EntitiesContainer();
        tileMap = new TileMap(config);
        entityMap = switch (config.getMapVariant()) {
            case GLOBE -> new Globe(config, entitiesContainer, tileMap);
            case HELLISH_PORTAL -> new HellishPortal(config, entitiesContainer, tileMap);
        };
        EntitiesEngine entitiesEngine = new EntitiesEngine(config, this, entitiesContainer, entityMap, tileMap);

        drawHeaders();
        updateGrid();
        HBox hBox = new HBox(grid);
        Scene scene = new Scene(hBox, windowSize.x, windowSize.y);
        stage.setScene(scene);
        stage.show();
        new Thread(entitiesEngine).start();
    }

    public void updateGrid() {
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
}
