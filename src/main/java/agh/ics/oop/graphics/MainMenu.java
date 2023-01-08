package agh.ics.oop.graphics;

import agh.ics.oop.config.ConfigSelector;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.simulation.Simulation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class MainMenu extends Application {
    private final Vector windowSize = new Vector(1400, 1000);

    @Override
    public void start(Stage primaryStage) throws Exception {
        new ImageContainer(List.of(new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW", "plant"}));

        Button refreshComboBox = new Button("Refresh");
        Button startButton = new Button("Start the simulation");
        ConfigSelector configSelector = new ConfigSelector("src/main/resources/config");
        HBox hBox = new HBox(configSelector.getComboBox(), refreshComboBox, startButton);

        refreshComboBox.setOnAction(actionEvent -> configSelector.reload());

        startButton.setOnAction(actionEvent -> {
            try {
                new Simulation("src/main/resources/config/" + configSelector.getComboBox().getValue(), true);
            } catch (IOException exception) {
                out.println(exception.getMessage());
            } catch (ParseException exception) {
                out.println("Invalid selected file contents.");
            }
        });


        Scene scene = new Scene(hBox, windowSize.x, windowSize.y);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
