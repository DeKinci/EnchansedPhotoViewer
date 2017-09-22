package main.java.photoviewer.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.java.photoviewer.GUI.tabs.contenttab.ContentTabFactory;
import main.java.photoviewer.GUI.tabs.newtab.NewTabFactory;
import main.java.photoviewer.core.PVFile;


public class Controller {
    PVFile pvFile = new PVFile();
    private NewTabFactory newTabFactory;
    private ContentTabFactory contentTabFactory;

    @FXML
    TabPane tabPane;

    public Controller() {
        newTabFactory = NewTabFactory.getNewTabFactory();
        contentTabFactory = ContentTabFactory.getNewTabFactory();
    }

    @FXML
    public void initialize() {
        tabPane.getTabs().add(newTabFactory.getTab("Main"));
    }


    @FXML
    public void openFileMenuClick() {
        Tab tab = contentTabFactory.getTab();
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    @FXML
    public void saveFileMenuClick() {

    }

    @FXML
    public void importFileMenuClick() {

    }

    @FXML
    public void exportFileMenuClick() {

    }

    @FXML
    public void closeFileMenuClick() {

    }
}
