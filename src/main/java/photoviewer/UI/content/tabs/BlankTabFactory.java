package photoviewer.UI.content.tabs;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;

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

    public Tab getTab() {
        return loadFXML(new Tab());
    }

    public Tab getTab(String name) {
        return loadFXML(new Tab(name));
    }

    private Tab loadFXML(Tab tab) {
        StackPane pane = null;

        try {
            pane = FXMLLoader.load(getClass().getResource("/fxml/tabs/blanktab/blanktab.fxml"));
        } catch (IOException e) {
            System.err.println("Error loading resource: blanktab");
            e.printStackTrace();
        }

        tab.setContent(pane);
        return tab;
    }
}
