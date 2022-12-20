package agh.ics.oop.main;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Terrain;
import agh.ics.oop.graphics.App;
import javafx.application.Application;
import org.json.simple.*;
import agh.ics.oop.core_classes.MapDirection;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        try {
            Config config = new Config("src/main/resources/config/config1.json");

            System.out.println("Hello World!");
            System.out.println(config.getMapSize());
            System.out.println(MapDirection.SW.rotateBy(4));
            System.out.println(Arrays.toString(MapDirection.values()));
            System.out.println(MapDirection.getRandom());

            Application.launch(App.class, args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
