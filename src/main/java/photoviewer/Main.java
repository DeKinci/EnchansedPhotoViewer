package photoviewer;

import javafx.application.Application;
import javafx.stage.Stage;
import photoviewer.UI.UI;

public class Main extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new UI(primaryStage);
    }
}