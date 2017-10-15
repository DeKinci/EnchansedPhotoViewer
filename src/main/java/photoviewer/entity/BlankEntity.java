package photoviewer.entity;

import photoviewer.entity.identification.IDProvider;

public class BlankEntity implements Entity {
    private final int id;

    public BlankEntity() {
        id = IDProvider.requestID();
    }
    
    @Override
    public int getID() {
        return id;
    }
}
