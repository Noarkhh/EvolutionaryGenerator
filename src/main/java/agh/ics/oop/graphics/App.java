package agh.ics.oop.graphics;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.EntitiesContainer;
import agh.ics.oop.entities.EntitiesEngine;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.maps.EntityMap;
import agh.ics.oop.maps.Globe;
import agh.ics.oop.maps.TileMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application {

    private EntityMap entityMap;
    private TileMap tileMap;

    private final Vector windowSize = new Vector(1000, 1000);
    private final Vector verticalHeaderCellSize = new Vector(25, 60);
    private final Vector horizontalHeaderCellSize = new Vector(60, 25);

    private final GridPane grid = new GridPane();
    private EntitiesEngine entitiesEngine;

    @Override
    public void start(Stage primaryStage) {
        updateGrid();
        Scene scene = new Scene(grid, windowSize.x, windowSize.y);
        primaryStage.setScene(scene);
        primaryStage.show();
        new Thread(entitiesEngine).start();
    }

    public void updateGrid() {
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.setGridLinesVisible(false);
        drawHeaders(grid);
        drawElements(grid);
        grid.setGridLinesVisible(true);
    }

    @Override
    public void init() throws Exception {
        super.init();
        List<String> imageNames = List.of(new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW", "plant"});
        ImageContainer imageContainer = new ImageContainer(imageNames);
        Config config = new Config("src/main/resources/config/config1.json");
        EntitiesContainer entitiesContainer = new EntitiesContainer();
        tileMap = new TileMap(config);
        entityMap = new Globe(config, entitiesContainer, tileMap);
        entitiesEngine = new EntitiesEngine(config, this, entitiesContainer, entityMap, tileMap);
//        entityMap.place(new Animal(new Vector(5, 5), entityMap, config, GenomeFactory.createGenome(config), new LinkedList<>()));
//        entityMap.place(new Animal(new Vector(5, 5), entityMap, config, GenomeFactory.createGenome(config), new LinkedList<>()));
        System.out.println(entityMap);
//        entitiesEngine.run();
//        System.out.println(entityMap);
//        entityMap.procreate();
    }

    private void drawHeaders(GridPane grid) {
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

    private void drawElements(GridPane grid) {

        for (int x = 0; x < entityMap.size.x; x++) {
            for (int y = 0; y < entityMap.size.y; y++) {
                drawElementFrom(grid, new Vector(x, y));
            }
        }
    }

    private void drawElementFrom(GridPane grid, Vector elementPosition) {
        List<Entity> entities = entityMap.objectsAt(elementPosition);
        if (entities == null) return;

//        GuiElementBox guiElementBox = new GuiElementBox(elementToDraw, spriteContainer);
        for (Entity entity : entities) {
            ImageView imageView = new ImageView(entity.getImage());
            imageView.setFitHeight(60);
            imageView.setFitWidth(60);

            grid.add(imageView, elementPosition.x + 1, elementPosition.y + 1);
            GridPane.setHalignment(imageView, HPos.CENTER);
        }
    }

    public void update() {
        Platform.runLater(this::updateGrid);
    }
}
