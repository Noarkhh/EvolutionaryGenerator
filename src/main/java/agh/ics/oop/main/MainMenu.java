package agh.ics.oop.main;

import agh.ics.oop.config.ConfigSelector;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.graphics.ImageContainer;
import agh.ics.oop.simulation.Simulation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.List;

import static java.lang.System.out;

public class MainMenu extends Application {
    private final Vector windowSize = new Vector(800, 600);

    @Override
    public void start(Stage primaryStage) {
        new ImageContainer(List.of(new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW", "plant"}));

        Text title = new Text("Evolutionary Generator");
        title.setFont(new Font(50));

        Button refreshComboBox = new Button("Refresh");
        Button startButton = new Button("Start the simulation");
        startButton.setPrefHeight(50);

        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(70, 30);

        ConfigSelector configSelector = new ConfigSelector("config");
        CheckBox csvCheckBox = new CheckBox("Log simulation statistics to CSV");

        HBox configBox = new HBox(configSelector.getComboBox(), refreshComboBox);
        configBox.setSpacing(20);
        configBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(title, startButton, configBox, csvCheckBox, quitButton);
        mainBox.setSpacing(30);
        mainBox.setAlignment(Pos.CENTER);

        refreshComboBox.setOnAction(actionEvent -> configSelector.reload());

        startButton.setOnAction(actionEvent -> {
            try {
                new Simulation(configSelector.getSelectedConfigPath(), csvCheckBox.isSelected());
            } catch (IOException exception) {
                out.println(exception.getMessage());
            } catch (ParseException exception) {
                out.println("Invalid selected file contents.");
            }
        });

        quitButton.setOnAction(actionEvent -> Platform.exit());

        Scene scene = new Scene(mainBox, windowSize.x, windowSize.y);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
