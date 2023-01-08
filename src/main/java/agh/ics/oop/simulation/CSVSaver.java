package agh.ics.oop.simulation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVSaver {
    private final Statistics statistics;
    private final File logsFile;

    public CSVSaver(String targetDirectoryPath, Statistics statistics) throws IOException {
        this.statistics = statistics;
        this.logsFile = new File(targetDirectoryPath + "/" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSS").format(new Date()) + ".csv");
        if (!logsFile.createNewFile()) throw new IOException();


    }

    public void logCurrentDay() {
        List<Object> stats = List.of(
                statistics.getNumberOfAnimals(),
                statistics.getNumberOfPlants(),
                statistics.getNumberOfEmptyTiles(),
                statistics.getMostPopularGenome(),
                statistics.getAverageEnergy(),
                statistics.getAverageLifespan());

        String csvLine = stats.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(csvLine);
        try {
            try (PrintWriter pw = new PrintWriter(logsFile)) {
                pw.println(csvLine);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
