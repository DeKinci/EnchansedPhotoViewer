package main.java.photoviewer.GUI.tabs.newtab;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import main.java.photoviewer.GUI.tabs.contenttab.ContentTabFactory;
import main.java.photoviewer.GUI.tabs.tabfactory.TabFactory;

import java.io.IOException;

public class NewTabFactory implements TabFactory {
    private volatile static NewTabFactory newTabFactory;

    public static NewTabFactory getNewTabFactory() {
        if (newTabFactory == null)
            synchronized (ContentTabFactory.class) {
                if (newTabFactory == null)
                    newTabFactory = new NewTabFactory();
            }

        return newTabFactory;
    }

    private NewTabFactory() {

    }

    @Override
    public Tab getTab() {
        return loadFXML(new Tab());
    }

    @Override
    public Tab getTab(String name) {
        return loadFXML(new Tab(name));
    }

    private Tab loadFXML(Tab tab) {
        AnchorPane pane = null;

        try {
            pane = FXMLLoader.load(getClass().getResource("newtab.fxml"));
        } catch (IOException e) {
            System.err.println("Error loading resource: newtab");
        }

        tab.setContent(pane);
        return tab;
    }
}
