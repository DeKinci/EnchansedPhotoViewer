package photoviewer.entity.mediaentity;

import photoviewer.entity.Entity;
import photoviewer.entity.identification.IDProvider;
import photoviewer.model.core.files.unit.PVUnit;

public class ImageEntity implements Entity {
    private final int id;

    private PVUnit unit;

    protected ImageEntity(PVUnit unit) {
        this.id = IDProvider.requestID();
        this.unit = unit;
    }

    @Override
    public int getID() {
        return id;
    }

    public PVUnit getUnit() {
        return this.unit;
    }
}
