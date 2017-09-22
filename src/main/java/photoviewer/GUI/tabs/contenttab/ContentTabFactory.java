package main.java.photoviewer.GUI.tabs.contenttab;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import main.java.photoviewer.GUI.tabs.tabfactory.TabFactory;

import java.io.IOException;

public class ContentTabFactory implements TabFactory {
    private volatile static ContentTabFactory newTabFactory;

    public static ContentTabFactory getNewTabFactory() {
        if (newTabFactory == null)
            synchronized (ContentTabFactory.class) {
                if (newTabFactory == null)
                    newTabFactory = new ContentTabFactory();
            }

        return newTabFactory;
    }

    private ContentTabFactory() {

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
        SplitPane pane = null;

        try {
            pane = FXMLLoader.load(getClass().getResource("contenttab.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tab.setContent(pane);
        return tab;
    }
}
