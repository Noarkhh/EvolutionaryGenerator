package agh.ics.oop.simulation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CSVLogger {
    private final SimulationStatistics statistics;
    private final File logsFile;

    public CSVLogger(String targetDirectoryPath, SimulationStatistics statistics) throws IOException {
        this.statistics = statistics;
        this.logsFile = new File(targetDirectoryPath + "/" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSS").format(new Date()) + ".csv");
        if (!logsFile.createNewFile()) throw new IOException();
        try {
            try (PrintWriter pw = new PrintWriter(new FileWriter(logsFile, true))) {
                pw.println("Animals,Plants,Empty Tiles,Most popular genome,Average energy, Average Lifespan");
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public void logCurrentDay() {
        List<Object> stats = List.of(
                statistics.getNumberOfAnimals(),
                statistics.getNumberOfPlants(),
                statistics.getNumberOfEmptyTiles(),
                statistics.getMostPopularGenome(),
                statistics.getAverageEnergy(),
                statistics.getAverageLifespan());

        String csvLine = stats.stream().map(String::valueOf).collect(Collectors.joining(";"));
        System.out.println(csvLine);
        try {
            try (PrintWriter pw = new PrintWriter(new FileWriter(logsFile, true))) {
                pw.println(csvLine);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
