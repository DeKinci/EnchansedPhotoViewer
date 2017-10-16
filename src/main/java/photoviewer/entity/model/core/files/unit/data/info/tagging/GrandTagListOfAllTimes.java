package photoviewer.entity.model.core.files.unit.data.info.tagging;

import ca.odell.glazedlists.BasicEventList;
import photoviewer.UI.content.tabs.blanktab.RecentlyOpenedFiles;

import java.io.*;

public class GrandTagListOfAllTimes implements Serializable {
    private BasicEventList<Tag> tagList;//uses eventlist to notify all users about tag change
    private static volatile GrandTagListOfAllTimes instance;
    private static final String stateSaver = "src/main/resources/statesaver/grandTagList.dat";

    public static GrandTagListOfAllTimes getInstance() {
        if (instance == null)
            synchronized (RecentlyOpenedFiles.class) {
                if (instance == null)
                    try {
                        FileInputStream fis = new FileInputStream(stateSaver);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        instance = (GrandTagListOfAllTimes) ois.readObject();
                        instance.init();
                    } catch (IOException | ClassNotFoundException e) {
                        try {
                            new File(stateSaver).createNewFile();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        instance = new GrandTagListOfAllTimes();
                    }
            }

        return instance;
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

    private GrandTagListOfAllTimes() {
        tagList = new BasicEventList<>();
        init();
    }

    public BasicEventList<Tag> getTagList() {
        return tagList;
    }

    public void clearGrandTagList(boolean areYouSure) {
        if (areYouSure)
            this.tagList.clear();
    }
}
