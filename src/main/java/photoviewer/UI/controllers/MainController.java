package main.java.photoviewer.UI.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import main.java.photoviewer.UI.content.tabs.blanktab.BlankTabFactory;
import main.java.photoviewer.UI.content.tabs.newtabbuttontab.NewTabButtonTabFactory;
import main.java.photoviewer.model.Application;

import java.io.File;


public class MainController {
    private Application application;

    private BlankTabFactory blankTabFactory;
    private NewTabButtonTabFactory newTabButtonTabFactory;

    @FXML
    TabPane tabPane;

    @FXML
    AnchorPane mainPane;

    public MainController() {
        blankTabFactory = BlankTabFactory.getInstance();
        newTabButtonTabFactory = NewTabButtonTabFactory.getInstance();

        this.application = Application.getApplication();
    }

    @FXML
    public void initialize() {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        tabPane.getTabs().addListener((ListChangeListener<Tab>) c -> {
            if (tabPane.getTabs().size() > 10)
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            else
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        });

        tabPane.getTabs().add(blankTabFactory.getTab("Main"));
        tabPane.getTabs().add(newTabButtonTabFactory.getNewTabButtonTab());
    }

    @FXML
    public void openFileMenuClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.epvi", "*.jpg", "*.png")
        );

        File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
        if (selectedFile != null)
            application.openFile(selectedFile);
    }

    @FXML
    public void saveFileMenuClick() {

    }

    @FXML
    public void exportFileMenuClick() {

    }

    @FXML
    public void closeFileMenuClick() {

    }
}
