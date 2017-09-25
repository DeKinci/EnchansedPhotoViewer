package photoviewer.UI.controllers.tabcontrollers.contenttabcontrollers;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import photoviewer.core.files.unit.PVUnit;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ContentTabController {
    private PVUnit unit;

    @FXML
    SplitPane unitPane;
    @FXML
    VBox infoBox;
    @FXML
    StackPane contentPane;
    @FXML
    ImageView imageView;

    @FXML
    public void initialize() {
    }

    public void initializeWithUnit(PVUnit unit) {
        this.unit = unit;
        setImageView();
        setInfoBox();

    }

    private void setImageView() {
        Image image = SwingFXUtils.toFXImage((BufferedImage) unit.getContent(), null);
        imageView.setImage(image);
    }

    private void setInfoBox() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tabs/contenttab/infopane.fxml"));
        SplitPane infoPane = null;

        try {
            infoPane = loader.load();
        } catch (IOException e) {
            System.err.println("Error loading infopane");
        }

        ContentInfoController contentInfoController = loader.getController();
        contentInfoController.initializeWithInfo(unit.getInfo());

        infoBox.getChildren().add(infoPane);
    }
}
