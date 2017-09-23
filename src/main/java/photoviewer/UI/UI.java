package main.java.photoviewer.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UI {
    private Stage stage;

    public UI(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        this.stage.setTitle("Enhanced Photo Viewer");

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/main/resources/gui/main.fxml"));
        primaryStage.setScene(new Scene(pane, 1280, 720));
        primaryStage.show();
    }
}
