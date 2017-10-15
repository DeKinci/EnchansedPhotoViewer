package photoviewer.model.instance.ImageInstance;

import javafx.scene.control.Tab;
import photoviewer.UI.content.tabs.contenttab.ContentTabFactory;
import photoviewer.model.core.files.unit.PVUnit;
import photoviewer.model.core.files.unit.factory.PVImageFactory;
import photoviewer.model.core.files.unit.factory.PVUnitFactory;
import photoviewer.model.instance.Instance;

import java.io.File;
import java.io.IOException;

public class ImageInstance implements Instance {
    private PVUnit unit;

    private Tab tab;

    private File openedFile;

    public ImageInstance(File file) throws IOException {
        this.openedFile = file;

        PVUnitFactory pvUnitFactory = PVImageFactory.getInstance();
        this.unit = pvUnitFactory.openFile(file);

        ContentTabFactory contentTabFactory = ContentTabFactory.getInstance();
        this.tab = contentTabFactory.getTab(this.unit);
    }

    @Override
    public PVUnit getUnit() {
        return unit;
    }

    @Override
    public Tab getTab() {
        return tab;
    }

    @Override
    public File getOpenedFile() {
        return openedFile;
    }
}
