package photoviewer.entity.model.entities;

import javafx.scene.control.Tab;
import photoviewer.UI.content.tabs.BlankTabFactory;
import photoviewer.entity.model.identification.IDProvider;

import java.io.File;

public class BlankEntity implements Entity {
    private final int id;
    private Tab tab;

    public BlankEntity() {
        this(null);
    }

    public BlankEntity(String s) {
        id = IDProvider.requestID();
        tab = BlankTabFactory.getInstance().getTab(s);
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
        throw new UnsupportedOperationException("Can't save blank entity");
    }

    @Override
    public void export(File file) {
        throw new UnsupportedOperationException("Can't export blank entity");
    }
}
