package photoviewer.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import photoviewer.UI.content.tabs.blanktab.RecentlyOpenedFiles;
import photoviewer.model.instance.Instance;
import photoviewer.model.instance.InstanceFactory;

import java.io.File;
import java.io.IOException;

public class Application {
    private volatile static Application application = new Application();

    private ObservableList<Instance> instances;
    private InstanceFactory instanceFactory;

    public static Application getApplication() {
        return application;
    }

    private Application() {
        instances = FXCollections.observableArrayList();
        instanceFactory = InstanceFactory.getInstanceFactory();
    }

    public ObservableList<Instance> getInstances() {
        return instances;
    }

    public void openFile(File file) {
        try {
            this.instances.add(instanceFactory.openFile(file));
            RecentlyOpenedFiles.getInstance().addFile(file);
            System.out.println("File " + file + " opened");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
