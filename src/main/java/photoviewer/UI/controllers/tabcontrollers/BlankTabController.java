package photoviewer.UI.controllers.tabcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import photoviewer.UI.content.tabs.blanktab.RecentlyOpenedFiles;
import photoviewer.model.Application;

import java.io.File;

public class BlankTabController {
    private Application application;
    private RecentlyOpenedFiles recentlyOpenedFilesList;

    @FXML
    ListView recentFilesView;

    public BlankTabController() {
        application = Application.getApplication();

        recentlyOpenedFilesList = RecentlyOpenedFiles.getInstance();
    }

    @FXML
    public void initialize() {
        recentFilesView.setItems(recentlyOpenedFilesList.getRecentlyOpenedFiles());

        recentFilesView.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    File file = (File) recentFilesView.getSelectionModel().getSelectedItem();
                    if (file != null)
                        application.openFile(file);
                }
        });
    }

    @FXML
    public void clearButton() {
        recentlyOpenedFilesList.clearFiles();
    }
}
