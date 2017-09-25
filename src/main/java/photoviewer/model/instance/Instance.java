package photoviewer.model.instance;

import javafx.scene.control.Tab;
import photoviewer.core.files.unit.PVUnit;

import java.io.File;

public interface Instance {
    PVUnit getUnit();

    Tab getTab();

    File getOpenedFile();
}
