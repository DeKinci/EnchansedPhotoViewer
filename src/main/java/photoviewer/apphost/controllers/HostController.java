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
import photoviewer.entity.model.BlankEntity;
import photoviewer.entity.model.Entity;

import java.io.File;
import java.io.FileNotFoundException;
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
        Entity changed = (Entity) arg;
        if (doesEntityPresents(changed))
            removeTabByEntity(changed);
        else
            addEntity(changed);
    }

    private void addEntity(Entity entity) {
        entityPane.getTabs().add(entity.getTab());
    }

    private boolean doesEntityPresents(Entity entity) {
        for (Tab tab : entityPane.getTabs())
            if (tab.equals(entity.getTab()))
                return true;
        return false;
    }

    private void removeTabByEntity(Entity entity) {
        for (Tab tab : entityPane.getTabs())
            if (tab.equals(entity.getTab())) {
                entityPane.getTabs().remove(tab);
                return;
            }
    }

    private Entity getEntityByTab(Tab tab) {
        Iterator iterator = host.createIterator();
        while (iterator.hasNext()) {
            Entity entity = (Entity) iterator.next();
            if (entity.getTab().equals(tab))
                return entity;
        }
        return null;
    }
}
