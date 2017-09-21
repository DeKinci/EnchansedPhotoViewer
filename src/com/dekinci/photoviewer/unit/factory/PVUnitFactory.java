package com.dekinci.photoviewer.unit.factory;

import com.dekinci.photoviewer.unit.PVUnit;

import java.io.File;
import java.io.IOException;

public interface PVUnitFactory {
    PVUnit importFile(File source) throws IOException;

    PVUnit openFile(File source) throws IOException, ClassNotFoundException;
}
