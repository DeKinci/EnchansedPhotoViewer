package main.java.photoviewer.GUI.tabs.newtab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.File;

public class NewTabController {

    @FXML
    ListView recentFilesView;

    private ObservableList<File> recentFiles = FXCollections.observableArrayList(new File("misc/img.jpg"), new File("misc/out.png"));

    @FXML
    public void initialize() {
        recentFilesView.setItems(recentFiles);

        recentFilesView.setOnMouseClicked(event -> {
                //TODO
        });
    }
}
