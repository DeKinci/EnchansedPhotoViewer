package photoviewer.apphost;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UI {
    private Stage stage;

    public UI(Stage primaryStage) {
        this.stage = primaryStage;
        this.stage.setTitle("Enhanced Photo Viewer");

        BorderPane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("/host/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setScene(new Scene(pane, 1280, 720));
        primaryStage.show();
    }
}
