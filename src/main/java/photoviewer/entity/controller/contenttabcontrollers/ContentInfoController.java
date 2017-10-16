package photoviewer.entity.controller.contenttabcontrollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import photoviewer.entity.model.core.files.unit.data.info.Info;
import photoviewer.entity.model.core.files.unit.data.info.tagging.Tag;

import java.io.IOException;

public class ContentInfoController {
    private Info info;

    @FXML
    BorderPane mainPane;

    @FXML
    VBox tagBox;

    @FXML
    FlowPane tagPane;
    @FXML
    Button addTagButton;
    @FXML
    TextField addedTag;

    @FXML
    VBox otherBox;

    @FXML
    VBox ratingBox;
    @FXML
    ProgressBar ratingBar;

    @FXML
    VBox attStatBox;
    @FXML
    LineChart attStatChart;
    @FXML
    Button addLike;
    @FXML
    Button addDislike;

    @FXML
    public void initialize() {
    }

    public void initializeWithInfo(Info info) {
        this.info = info;
        addTags();
    }

    private void addTags() {
        for (Tag tag : this.info.getTags().getTags()) {
            Button tagButton = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tabs/contenttab/tagButton.fxml"));

            try {
                tagButton = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TagButtonController tagButtonController = loader.getController();
            tagButtonController.initializeWithTag(tag);

            tagPane.getChildren().add(tagButton);
        }
    }

    @FXML
    public void addTagClick() {
        String tagName = addedTag.getText();
        if (!tagName.equals("")) {
            Button tagButton = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tabs/contenttab/tagButton.fxml"));

            try {
                tagButton = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            TagButtonController tagButtonController = loader.getController();
            tagButtonController.initializeWithTag(new Tag(tagName));

            tagPane.getChildren().add(tagButton);
            addedTag.setText("");
        }
    }
}
