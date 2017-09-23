package main.java.photoviewer.UI.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import main.java.photoviewer.UI.content.tabs.contenttab.ContentTabFactory;
import main.java.photoviewer.UI.content.tabs.blanktab.BlankTabFactory;
import main.java.photoviewer.UI.content.tabs.newtabbuttontab.NewTabButtonTabFactory;
import main.java.photoviewer.core.files.PVInstance;


public class MainController {
    PVInstance pvFile = new PVInstance();
    private BlankTabFactory blankTabFactory;
    private ContentTabFactory contentTabFactory;
    private NewTabButtonTabFactory newTabButtonTabFactory;

    @FXML
    TabPane tabPane;

    public MainController() {
        blankTabFactory = BlankTabFactory.getInstance();
        contentTabFactory = ContentTabFactory.getInstance();
        newTabButtonTabFactory = NewTabButtonTabFactory.getInstance();
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
        Tab tab = contentTabFactory.getTab();
        tab.getContent().setScaleX(tabPane.getScaleX());
        tab.getContent().setScaleY(tabPane.getScaleY());

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
