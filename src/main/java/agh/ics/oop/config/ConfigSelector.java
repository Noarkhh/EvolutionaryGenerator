package agh.ics.oop.config;

import javafx.scene.control.ComboBox;

import java.io.File;
import java.util.Objects;

public class ConfigSelector {
    private ComboBox<String> comboBox = new ComboBox<>();

    public ConfigSelector(String configFolderPath) {
        for (File file : Objects.requireNonNull(new File(configFolderPath).listFiles())) {
            if (!file.isDirectory() && isJSON(file)) {
                comboBox.getItems().add(file.getName());
            }
        }
        if (comboBox.getItems().size() > 0) comboBox.setValue(comboBox.getItems().get(0));
        comboBox.setMinWidth(150);
    }

    private boolean isJSON (File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return false;
        }
        return name.substring(lastIndexOf).equals(".json");
    }

    public ComboBox<String> getComboBox() {
        return comboBox;
    }
}
