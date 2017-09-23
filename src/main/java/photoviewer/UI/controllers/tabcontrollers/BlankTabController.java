package main.java.photoviewer.UI.controllers.tabcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import main.java.photoviewer.UI.content.tabs.blanktab.RecentlyOpenedFiles;
import main.java.photoviewer.model.Application;

import java.io.File;

public class BlankTabController {
    Application application;
    RecentlyOpenedFiles recentlyOpenedFilesList;

    @FXML
    ListView recentFilesView;

    public BlankTabController() {
        application = Application.getApplication();

        recentlyOpenedFilesList = RecentlyOpenedFiles.getInstance();
    }

    @FXML
    public void initialize() {
        recentFilesView.setItems(recentlyOpenedFilesList.getRecentlyOpenedFilesObservableList());

        recentFilesView.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1)
                    application.openFile((File) recentFilesView.getSelectionModel().getSelectedItem());
        });
    }

    @FXML
    public void clearButton() {
        recentlyOpenedFilesList.clearFiles();
    }
}
