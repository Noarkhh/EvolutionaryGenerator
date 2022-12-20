package agh.ics.oop.main;

import agh.ics.oop.config.Config;
import agh.ics.oop.core_classes.Terrain;
import agh.ics.oop.core_classes.Vector;
import agh.ics.oop.entities.Animal;
import agh.ics.oop.genes.*;
import agh.ics.oop.graphics.App;
import agh.ics.oop.graphics.ImageContainer;
import agh.ics.oop.maps.EntityMap;
import agh.ics.oop.maps.Globe;
import agh.ics.oop.maps.TileMap;
import javafx.application.Application;
import org.json.simple.*;
import agh.ics.oop.core_classes.MapDirection;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        try {
            List<String> imageNames = List.of(new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW", "plant"});
            ImageContainer imageContainer = new ImageContainer(imageNames);
            Config config = new Config("src/main/resources/config/config1.json");
            TileMap tileMap = new TileMap(config);
            EntityMap entityMap = new Globe(config, tileMap);
            Genome genome1 = GenomeFactory.createGenome(config);
            Genome genome2 = GenomeFactory.createGenome(config);
            Animal animal1 = new Animal(new Vector(0, 0), entityMap, config, genome1, 50);
            Animal animal2 = new Animal(new Vector(0, 0), entityMap, config, genome2, 200);

            System.out.println(genome1);
            System.out.println(genome2);
            System.out.println(Arrays.toString(new NonDarwinianRecombinator().recombine(animal1, animal2)));
            System.out.println(Arrays.toString(new CorrectingMutator(config).mutate(new NonDarwinianRecombinator().recombine(animal1, animal2))));

            System.out.println(config.getGenomeLength());

//            Application.launch(App.class, args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
