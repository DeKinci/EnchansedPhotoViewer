package photoviewer.entity.model;

import java.io.File;

public interface EntityFactory {
    Entity getMediaEntity(File file);
}
