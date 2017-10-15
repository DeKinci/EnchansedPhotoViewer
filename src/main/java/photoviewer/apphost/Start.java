package photoviewer.apphost;

import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new photoviewer.apphost.UI(primaryStage);
    }
}
