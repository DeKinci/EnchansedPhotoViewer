package photoviewer.UI.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import photoviewer.UI.content.tabs.blanktab.BlankTabFactory;
import photoviewer.UI.content.tabs.newtabbuttontab.NewTabButtonTab;
import photoviewer.model.Application;
import photoviewer.model.instance.Instance;

import java.io.File;


public class MainController {
    private Application application;

    private BlankTabFactory blankTabFactory;
    private NewTabButtonTab newTabButtonTabFactory;

    @FXML
    TabPane tabPane;

    @FXML
    AnchorPane mainPane;

    public MainController() {
        blankTabFactory = BlankTabFactory.getInstance();
        newTabButtonTabFactory = NewTabButtonTab.getInstance();

        this.application = Application.getApplication();
    }

    @FXML
    public void initialize() {//TODO: split this method into many submethods
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        tabPane.getTabs().addListener((ListChangeListener<Tab>) c -> {
            if (tabPane.getTabs().size() > 10)
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            else
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        });

        tabPane.getTabs().add(blankTabFactory.getTab("Main"));
        tabPane.getTabs().add(newTabButtonTabFactory.getNewTabButtonTab());
        application.getInstances().addListener((ListChangeListener<Instance>) c -> {
            while (c.next()) {
                if (c.wasAdded())
                    for (Instance a : c.getAddedSubList()) {
                        tabPane.getTabs().add(a.getTab());
                        tabPane.getSelectionModel().select(a.getTab());
                    }

                if (c.wasRemoved())
                    for (Instance a : c.getRemoved())
                        tabPane.getTabs().remove(a.getTab());
            }
        });
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
        tabPane.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void exportFileMenuClick() {

    }

    @FXML
    public void closeFileMenuClick() {
    }
}
