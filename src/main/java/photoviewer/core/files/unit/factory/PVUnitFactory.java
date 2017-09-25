package photoviewer.core.files.unit.factory;

import photoviewer.core.files.unit.PVUnit;

import java.io.File;
import java.io.IOException;

public interface PVUnitFactory {
    PVUnit openFile(File source) throws IOException;
}
