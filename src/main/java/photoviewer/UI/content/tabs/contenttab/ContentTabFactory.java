package photoviewer.UI.content.tabs.contenttab;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import photoviewer.UI.content.tabs.tabfactory.TabFactory;
import photoviewer.entity.controller.contenttabcontrollers.ContentTabController;
import photoviewer.entity.model.core.files.unit.PVUnit;

import java.io.IOException;

public class ContentTabFactory implements TabFactory {
    private volatile static ContentTabFactory newTabFactory;

    public static ContentTabFactory getInstance() {
        if (newTabFactory == null)
            synchronized (ContentTabFactory.class) {
                if (newTabFactory == null)
                    newTabFactory = new ContentTabFactory();
            }

        return newTabFactory;
    }

    private ContentTabFactory() {
    }

    public Tab getTab(PVUnit unit) {
        Tab tab = new Tab(unit.toString());
        builtTab(tab, unit);

        return tab;
    }

    private void builtTab(Tab tab, PVUnit unit) {
        SplitPane pane = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tabs/contenttab/contenttab.fxml"));

        try {
            pane = loader.load();
        } catch (IOException e) {
            System.err.println("Error loading content: contenttab");
        }

        ContentTabController contentTabController = loader.getController();
        contentTabController.initializeWithUnit(unit);

        tab.setContent(pane);
    }
}
