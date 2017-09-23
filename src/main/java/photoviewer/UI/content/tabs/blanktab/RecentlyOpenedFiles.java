package main.java.photoviewer.UI.content.tabs.blanktab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class RecentlyOpenedFiles implements Serializable {
    private static final String stateSaver = "res/statesaver/recentlyOpenedFilesList.dat";
    private static final long serialVersionUID = 1L;

    private volatile static RecentlyOpenedFiles instance;

    private ArrayList<File> recentlyOpenedFilesList;
    private transient ObservableList<File> recentlyOpenedFilesObservableList;

    public static RecentlyOpenedFiles getInstance() {
        if (instance == null)
            synchronized (RecentlyOpenedFiles.class) {
            if (instance == null)
                try {
                    FileInputStream fis = new FileInputStream(stateSaver);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    instance = (RecentlyOpenedFiles) ois.readObject();
                    instance.init();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    instance = new RecentlyOpenedFiles();
                }
            }

        return instance;
    }

    private RecentlyOpenedFiles() {
        recentlyOpenedFilesList = new ArrayList<>();
        init();
    }

    private void init() {
        recentlyOpenedFilesObservableList = FXCollections.observableArrayList(recentlyOpenedFilesList);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileOutputStream fos = new FileOutputStream(stateSaver);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this);
                oos.flush();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public void clearFiles() {
        this.recentlyOpenedFilesList.clear();
        this.recentlyOpenedFilesObservableList.clear();
    }

    public void addFile(File file) {
        this.recentlyOpenedFilesList.add(file);
        this.recentlyOpenedFilesObservableList.add(file);
    }

    public ObservableList<File> getRecentlyOpenedFilesObservableList() {
        return this.recentlyOpenedFilesObservableList;
    }

    public ArrayList<File> getRecentlyOpenedFilesList() {
        return this.recentlyOpenedFilesList;
    }
}
