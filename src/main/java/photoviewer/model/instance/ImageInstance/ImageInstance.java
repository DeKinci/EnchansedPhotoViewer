package main.java.photoviewer.model.instance.ImageInstance;

import javafx.scene.control.Tab;
import main.java.photoviewer.UI.content.tabs.contenttab.ContentTabFactory;
import main.java.photoviewer.UI.content.tabs.tabfactory.TabFactory;
import main.java.photoviewer.core.files.unit.PVUnit;
import main.java.photoviewer.core.files.unit.factory.PVImageFactory;
import main.java.photoviewer.core.files.unit.factory.PVUnitFactory;
import main.java.photoviewer.model.instance.Instance;

import java.io.File;
import java.io.IOException;

public class ImageInstance implements Instance {
    private PVUnit unit;
    private PVUnitFactory pvUnitFactory;

    private Tab tab;
    private TabFactory contentTabFactory;

    private File openedFile;

    public ImageInstance(File file) throws IOException {
        this.openedFile = file;

        this.pvUnitFactory = PVImageFactory.getInstance();
        this.unit = this.pvUnitFactory.openFile(file);

        this.contentTabFactory = ContentTabFactory.getInstance();
        this.tab = this.contentTabFactory.getTab(this.unit.toString());
    }

    public PVUnit getUnit() {
        return unit;
    }

    public Tab getTab() {
        return tab;
    }

    public File getOpenedFile() {
        return openedFile;
    }
}
