package agh.ics.oop.main;

import org.json.simple.*;
import agh.ics.oop.core_classes.MapDirection;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {

        try {
            JSONObject jo = (JSONObject) new JSONParser().parse(new FileReader("config/config1.json"));

            int test = ((Long) jo.get("mapWidth")).intValue();

            System.out.println("Hello World!");
            System.out.println(test);
            System.out.println(MapDirection.SW.rotateBy(4));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
