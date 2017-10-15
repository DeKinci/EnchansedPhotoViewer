package photoviewer.apphost.controllers;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import photoviewer.apphost.Host;
import photoviewer.apphost.fileopener.FileOpener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class HostController implements Observer {
    private Host host;
    private FileOpener fileOpener;
    private Logger log;

    @FXML
    TabPane instancePane;

    @FXML
    BorderPane mainPane;

    public HostController() {
        this.host = Host.getHost();
        log = LogManager.getLogger();
        fileOpener = FileOpener.getFileOpener();
    }

    @FXML
    public void initialize() {
        setAutoClosingTabPolicy();
        log.trace("Initializing host controller");
    }

    @FXML
    public void openFileMenuClick() {
        try {
            host.addInstance(fileOpener.openFile(selectFile()));
        } catch (FileNotFoundException e) {
            log.trace("Someway file is not opened");
        }
    }

    @FXML
    public void saveFileMenuClick() {
    }

    @FXML
    public void exportFileMenuClick() {
    }

    @FXML
    public void closeFileMenuClick() {
    }

    private void setAutoClosingTabPolicy() {
        instancePane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        instancePane.getTabs().addListener((ListChangeListener<Tab>) c -> {
            if (instancePane.getTabs().size() > 10)
                instancePane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            else
                instancePane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        });
    }

    private File selectFile() throws FileNotFoundException {
        File selectedFile = createFileChooser().showOpenDialog(mainPane.getScene().getWindow());
        validateFile(selectedFile);

        return selectedFile;
    }

    private FileChooser createFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.epvi", "*.jpg", "*.png")
        );

        return fileChooser;
    }

    private void validateFile(File file) throws FileNotFoundException {
        if (file == null)
            throw new FileNotFoundException();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
