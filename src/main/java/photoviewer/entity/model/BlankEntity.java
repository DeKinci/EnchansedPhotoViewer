package photoviewer.entity.model;

import javafx.scene.control.Tab;
import photoviewer.UI.content.tabs.blanktab.BlankTabFactory;
import photoviewer.entity.model.identification.IDProvider;

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
}
