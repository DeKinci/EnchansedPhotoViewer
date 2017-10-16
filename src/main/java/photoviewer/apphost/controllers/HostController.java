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
import photoviewer.entity.model.entities.BlankEntity;
import photoviewer.entity.model.entities.Entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

public class HostController implements Observer {
    private Host host;
    private FileOpener fileOpener;
    private Logger log;

    @FXML
    TabPane entityPane;

    @FXML
    BorderPane mainPane;

    public HostController() {
        this.host = Host.getHost();
        host.addObserver(this);

        log = LogManager.getLogger();
        fileOpener = FileOpener.getFileOpener();
    }

    @FXML
    public void initialize() {
        log.trace("Initializing host controller");
        setAutoClosingTabPolicy();

        host.addEntity(new BlankEntity("Main"));
    }

    @FXML
    public void openFileMenuClick() {
        try {
            fileOpener.openFile(selectFile());
        } catch (FileNotFoundException e) {
            log.trace("File is not choosed correctly");
        }
    }

    @FXML
    public void saveFileMenuClick() {
        Entity entity = getEntityByTab(
                entityPane.getSelectionModel().getSelectedItem());

        try {
            if (entity != null)
                entity.save(saveFile());
        } catch (FileNotFoundException e) {
            log.trace("File is not choosed correctly");
        }
    }

    @FXML
    public void exportFileMenuClick() {
    }

    @FXML
    public void closeFileMenuClick() {
        host.removeEntity(getEntityByTab(
                entityPane.getSelectionModel().getSelectedItem()));
    }

    private void setAutoClosingTabPolicy() {
        entityPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        entityPane.getTabs().addListener((ListChangeListener<Tab>) c -> {
            if (entityPane.getTabs().size() > 10)
                entityPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            else
                entityPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        });
    }

    private File selectFile() throws FileNotFoundException {
        File selectedFile = createOpenFileChooser().showOpenDialog(mainPane.getScene().getWindow());
        validateFile(selectedFile);

        return selectedFile;
    }

    private File saveFile() throws FileNotFoundException {
        File savingFile = createSaveFileChooser().showSaveDialog(mainPane.getScene().getWindow());
        validateFile(savingFile);

        try {
            if (!savingFile.createNewFile())
                showFileExistsMessage(savingFile.getName());
        } catch (IOException e) {
            log.trace(e.getMessage());
        }

        return savingFile;
    }

    private void showFileExistsMessage(String fileName) {
        log.trace("File " + fileName + " already exists, rewriting it");
    }

    private FileChooser createOpenFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files", "*.epvi", "*.jpg", "*.png"));

        return fileChooser;
    }

    private FileChooser createSaveFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("EPVI image", "*.epvi"));

        return fileChooser;
    }

    private void validateFile(File file) throws FileNotFoundException {
        if (file == null)
            throw new FileNotFoundException();
    }

    @Override
    public void update(Observable o, Object arg) {
        Entity changed = (Entity) arg;
        if (doesEntityPresents(changed))
            removeTabByEntity(changed);
        else
            addEntityTab(changed);
    }

    private void addEntityTab(Entity entity) {
        entityPane.getTabs().add(entity.getTab());
        entityPane.getSelectionModel().select(entity.getTab());
    }

    private boolean doesEntityPresents(Entity entity) {
        for (Tab tab : entityPane.getTabs())
            if (tab == entity.getTab())
                return true;
        return false;
    }

    private void removeTabByEntity(Entity entity) {
        for (Tab tab : entityPane.getTabs())
            if (tab == entity.getTab()) {
                entityPane.getTabs().remove(tab);
                return;
            }
    }

    private Entity getEntityByTab(Tab tab) {
        Iterator iterator = host.createIterator();
        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();
            if (entity.getTab() == tab)
                return entity;
        }
        return null;
    }
}
