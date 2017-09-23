package main.java.photoviewer.UI.content.tabs.newtabbuttontab;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;

import java.io.IOException;

/**
 * This "fake" tab always exists, so we don't use
 * conditional lock here
 */

public class NewTabButtonTabFactory {
    private static NewTabButtonTabFactory ourInstance = new NewTabButtonTabFactory();
    private Tab newTabButtonTab;

    public static NewTabButtonTabFactory getInstance() {
        return ourInstance;
    }

    private NewTabButtonTabFactory() {
        this.newTabButtonTab = new Tab();
        this.newTabButtonTab.setClosable(false);

        Button newTabButton = null; //TODO: Replace with an image
        try {
            newTabButton = FXMLLoader.load(getClass().getResource("/main/resources/gui/tabs/tabbuttons/newtabbutton.fxml"));
        } catch (IOException e) {
            System.err.println("Error loading newtabbutton.fxml");
        }

        this.newTabButtonTab.setDisable(true);

        this.newTabButtonTab.setGraphic(newTabButton);
    }

    public Tab getNewTabButtonTab() {
        return newTabButtonTab;
    }
}
