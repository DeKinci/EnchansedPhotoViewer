package photoviewer.entity.model.entities;

import javafx.scene.control.Tab;
import photoviewer.UI.content.tabs.ContentTabFactory;
import photoviewer.entity.model.core.unit.PVUnit;
import photoviewer.entity.model.identification.IDProvider;

import java.io.File;

public class ImageEntity implements Entity {
    private final int id;
    private Tab tab;

    private PVUnit unit;

    ImageEntity(PVUnit unit) {
        this.id = IDProvider.requestID();
        this.unit = unit;
        tab = ContentTabFactory.getInstance().getTab(unit);
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public Tab getTab() {
        return this.tab;
    }

    @Override
    public void save(File file) {
        this.unit.saveFile(file);
    }

    @Override
    public void export(File file) {
        this.unit.exportContent(file);
    }

    public PVUnit getUnit() {
        return this.unit;
    }
}
