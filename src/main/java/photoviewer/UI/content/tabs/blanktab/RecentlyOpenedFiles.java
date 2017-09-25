package photoviewer.UI.content.tabs.blanktab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class RecentlyOpenedFiles implements Serializable {
    private static final String stateSaver = "src/main/resources/statesaver/recentlyOpenedFiles.dat";
    private static final long serialVersionUID = 1L;

    private volatile static RecentlyOpenedFiles instance;

    private ObservableList<File> recentlyOpenedFiles;

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
                        try {
                            if (!new File(stateSaver).createNewFile())
                                System.err.println("Save file exists but damaged");
                        } catch (IOException e1) {
                            System.err.println("Something wrong has happened...");
                        }
                        instance = new RecentlyOpenedFiles();
                    }
            }

        return instance;
    }

    private RecentlyOpenedFiles() {
        recentlyOpenedFiles = FXCollections.observableArrayList();
        init();
    }

    private void init() {
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
        this.recentlyOpenedFiles.clear();
    }

    public void addFile(File file) {
        if (this.recentlyOpenedFiles.contains(file)) {//Not to allow the same file to be twice in the list
            this.recentlyOpenedFiles.remove(file);
        }

        this.recentlyOpenedFiles.add(0, file);
    }

    public ObservableList<File> getRecentlyOpenedFiles() {
        return this.recentlyOpenedFiles;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        ArrayList fileArrayList = new ArrayList(recentlyOpenedFiles);
        oos.writeObject(fileArrayList);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ArrayList fileArrayList = (ArrayList) ois.readObject();
        recentlyOpenedFiles = FXCollections.observableArrayList(fileArrayList);
    }
}
