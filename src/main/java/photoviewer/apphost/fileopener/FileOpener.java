package photoviewer.apphost.fileopener;

import photoviewer.apphost.Host;
import photoviewer.entity.model.entities.EntityFactory;
import photoviewer.entity.model.entities.ImageEntityFactory;

import java.io.File;

public class FileOpener {
    private static volatile FileOpener fileOpener;
    private EntityFactory factory;
    private Host host;

    public static FileOpener getFileOpener() {
        if (fileOpener == null)
            synchronized (FileOpener.class) {
                if (fileOpener == null)
                    fileOpener = new FileOpener();
            }

        return fileOpener;
    }

    private FileOpener() {
        host = Host.getHost();
        factory = ImageEntityFactory.getFactory();
    }

    public void openFile(File file) {
        host.addEntity(factory.getMediaEntity(file));
    }
}
