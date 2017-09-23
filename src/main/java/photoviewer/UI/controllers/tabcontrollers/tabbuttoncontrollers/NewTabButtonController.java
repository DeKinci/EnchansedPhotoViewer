package main.java.photoviewer.UI.controllers.tabcontrollers.tabbuttoncontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class NewTabButtonController {
    @FXML
    Button newTabButton;

    @FXML
    public void initialize() {
        newTabButton = new Button("+");
    }
}
