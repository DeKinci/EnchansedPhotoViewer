package photoviewer.entity.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import photoviewer.UI.content.tabs.blanktab.RecentlyOpenedFiles;
import photoviewer.apphost.fileopener.FileOpener;

import java.io.File;

public class BlankTabController {
    private RecentlyOpenedFiles recentlyOpenedFilesList;
    private FileOpener fileOpener;
    private Logger log;

    @FXML
    ListView recentFilesView;

    public BlankTabController() {
        recentlyOpenedFilesList = RecentlyOpenedFiles.getInstance();
        fileOpener = FileOpener.getFileOpener();
        this.log = LogManager.getLogger();
    }

    @FXML
    public void initialize() {
        recentFilesView.setItems(recentlyOpenedFilesList.getRecentlyOpenedFiles());

        recentFilesView.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    File selectedItem = (File) recentFilesView.getSelectionModel().getSelectedItem();
                    if (selectedItem != null)
                        fileOpener.openFile(selectedItem);
                    else
                        log.trace("Someway file is not opened");
                }
        });
    }

    @FXML
    public void clearButton() {
        recentlyOpenedFilesList.clearFiles();
    }
}
