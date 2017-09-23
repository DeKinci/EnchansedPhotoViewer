package main.java.photoviewer.UI.content.tabs.blanktab;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import main.java.photoviewer.UI.content.tabs.contenttab.ContentTabFactory;
import main.java.photoviewer.UI.content.tabs.tabfactory.TabFactory;

import java.io.IOException;

public class BlankTabFactory implements TabFactory {
    private volatile static BlankTabFactory blankTabFactory;

    public static BlankTabFactory getInstance() {
        if (blankTabFactory == null)
            synchronized (ContentTabFactory.class) {
                if (blankTabFactory == null)
                    blankTabFactory = new BlankTabFactory();
            }

        return blankTabFactory;
    }

    private BlankTabFactory() {

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
            pane = FXMLLoader.load(getClass().getResource("/main/resources/gui/tabs/blanktab.fxml"));
        } catch (IOException e) {
            System.err.println("Error loading resource: blanktab");
        }

        tab.setContent(pane);
        return tab;
    }
}
