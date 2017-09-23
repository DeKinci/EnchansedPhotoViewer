package main.java.photoviewer.UI.content.tabs.tabfactory;

import javafx.scene.control.Tab;

public interface TabFactory {
    Tab getTab();

    Tab getTab(String name);
}
