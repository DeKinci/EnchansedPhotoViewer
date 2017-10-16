package photoviewer.entity.model.mediaentity;

import javafx.scene.control.Tab;
import photoviewer.UI.content.tabs.contenttab.ContentTabFactory;
import photoviewer.entity.model.Entity;
import photoviewer.entity.model.identification.IDProvider;
import photoviewer.entity.model.core.files.unit.PVUnit;

public class ImageEntity implements Entity {
    private final int id;
    private Tab tab;

    private PVUnit unit;

    protected ImageEntity(PVUnit unit) {
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

    public PVUnit getUnit() {
        return this.unit;
    }
}
