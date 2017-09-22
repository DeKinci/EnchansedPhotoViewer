package main.java.photoviewer.GUI.tabs.tabfactory;

import javafx.scene.control.Tab;

public interface TabFactory {
    Tab getTab();

    Tab getTab(String name);
}
