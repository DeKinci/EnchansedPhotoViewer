package main.java.photoviewer.core.files.unit.factory;

import main.java.photoviewer.core.files.unit.PVUnit;

import java.io.File;
import java.io.IOException;

public interface PVUnitFactory {
    PVUnit openFile(File source) throws IOException;
}
