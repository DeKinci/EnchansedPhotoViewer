package photoviewer.entity.model.core.unit;

import java.io.File;
import java.io.IOException;

public interface PVUnitFactory {
    PVUnit openFile(File source) throws IOException;
}
