package photoviewer.entity.model.entities;

import java.io.File;

public interface EntityFactory {
    Entity getMediaEntity(File file);
}
