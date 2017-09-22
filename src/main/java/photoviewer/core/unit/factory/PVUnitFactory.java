package main.java.photoviewer.core.unit.factory;

import main.java.photoviewer.core.unit.PVUnit;

import java.io.File;
import java.io.IOException;

public interface PVUnitFactory {
    PVUnit importFile(File source) throws IOException;

    PVUnit openFile(File source) throws IOException, ClassNotFoundException;
}
