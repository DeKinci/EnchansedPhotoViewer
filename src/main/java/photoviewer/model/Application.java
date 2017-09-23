package main.java.photoviewer.model;

import main.java.photoviewer.UI.content.tabs.blanktab.RecentlyOpenedFiles;
import main.java.photoviewer.model.instance.Instance;
import main.java.photoviewer.model.instance.InstanceFactory;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Application {
    private volatile static Application application = new Application();

    private LinkedList<Instance> instances;
    private InstanceFactory instanceFactory;

    public static Application getApplication() {
        return application;
    }

    private Application() {
        instances = new LinkedList<>();
        instanceFactory = InstanceFactory.getInstanceFactory();
    }

    public LinkedList<Instance> getInstances() {
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
