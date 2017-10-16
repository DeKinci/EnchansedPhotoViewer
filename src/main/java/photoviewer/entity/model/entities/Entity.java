package photoviewer.entity.model.entities;

import javafx.scene.control.Tab;

import java.io.File;

public interface Entity {
    int getID();
    Tab getTab();

    void save(File file);
    void export(File file);
}