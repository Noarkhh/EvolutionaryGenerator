package agh.ics.oop.graphics;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.IPositionObserver;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.entities.Sprite;
import agh.ics.oop.maps.EntityMap;
import agh.ics.oop.maps.Globe;
import agh.ics.oop.maps.HellishPortal;
import agh.ics.oop.maps.TileMap;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class App extends Application implements IPositionObserver {

    private EntityMap entityMap;
    private TileMap tileMap;

    private final Vector windowSize = new Vector(1000, 1000);
    private final Vector verticalHeaderCellSize = new Vector(25, 40);
    private final Vector horizontalHeaderCellSize = new Vector(40, 25);

    private final GridPane grid = new GridPane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        updateGrid();
        Scene scene = new Scene(grid, windowSize.x, windowSize.y);
        primaryStage.setScene(scene);
        primaryStage.show();
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
        List<String> imageNames = List.of(new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"});
        ImageContainer imageContainer = new ImageContainer(imageNames);
        Config config = new Config("src/main/resources/config/config1.json");
        entityMap = new Globe(config);
    }

    private void drawHeaders(GridPane grid) {
        Vector lowerLeft = new Vector(0, 0), upperRight = entityMap.size;

        Label xy = new Label("x\\y");
        GridPane.setHalignment(xy, HPos.CENTER);
        grid.add(xy, 0, 0);
        grid.getColumnConstraints().add(new ColumnConstraints(verticalHeaderCellSize.x));
        grid.getRowConstraints().add(new RowConstraints(horizontalHeaderCellSize.y));

        for (int index = lowerLeft.x, i = 1; index <= upperRight.x; index++, i++) {
            Label label = new Label(String.format("%2d", index));
            grid.add(label, i, 0);
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(horizontalHeaderCellSize.x));
        }

        for (int index = upperRight.y, i = 1; index >= lowerLeft.y; index--, i++) {
            Label label = new Label(String.format("%2d", index));
            grid.add(label, 0, i);
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(verticalHeaderCellSize.y));
        }

    }

    private void drawElements(GridPane grid) {
        Vector lowerLeft = new Vector(0, 0), upperRight = entityMap.size;

        for (int x = lowerLeft.x, i = 1; x <= upperRight.x; x++, i++) {
            for (int y = upperRight.y, j = 1; y >= lowerLeft.y; y--, j++) {
                drawElementFrom(grid, new Vector(x, y), new Vector(i, j));
            }
        }
    }

    private void drawElementFrom(GridPane grid, Vector elementPosition, Vector gridDrawPosition) {
        List<Entity> entities = entityMap.objectsAt(elementPosition);
        if (entities == null) return;

//        GuiElementBox guiElementBox = new GuiElementBox(elementToDraw, spriteContainer);
        ImageView imageView = new ImageView(entities.get(0).getImage());

        grid.add(imageView, gridDrawPosition.x, gridDrawPosition.y);
        GridPane.setHalignment(imageView, HPos.CENTER);
    }

    @Override
    public void positionChanged(Animal movedAnimal, Vector oldPosition, Vector newPosition) {

    }

    @Override
    public void removedFrom(Entity removedEntity, Vector position) {

    }
}
