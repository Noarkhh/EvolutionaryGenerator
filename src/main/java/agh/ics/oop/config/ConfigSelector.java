package agh.ics.oop.config;

import javafx.scene.control.ComboBox;

import java.io.File;
import java.util.Objects;

public class ConfigSelector {
    private final ComboBox<String> comboBox = new ComboBox<>();
    private final File configFolder;

    public ConfigSelector(String configFolderPath) {
        this.configFolder = new File(configFolderPath);
        reload();
        comboBox.setMinWidth(150);
    }

    public void reload() {
        comboBox.getItems().clear();
        if (configFolder.listFiles() == null) return;
        for (File file : Objects.requireNonNull(configFolder.listFiles())) {
            if (!file.isDirectory() && isJSON(file)) {
                comboBox.getItems().add(file.getName());
            }
        }
        if (comboBox.getItems().size() > 0) comboBox.setValue(comboBox.getItems().get(0));
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

    public String getSelectedConfigPath() {
        return configFolder + "/" + comboBox.getValue();
    }
}
