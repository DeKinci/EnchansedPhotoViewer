package photoviewer.model.instance;

import photoviewer.model.instance.ImageInstance.ImageInstance;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;

public class InstanceFactory {
    private volatile static InstanceFactory instanceFactory;

    public static InstanceFactory getInstanceFactory() {
        if (instanceFactory == null)
            synchronized (InstanceFactory.class) {
            if (instanceFactory == null)
                instanceFactory = new InstanceFactory();
            }

        return instanceFactory;
    }

    private InstanceFactory() {
    }

    public Instance openFile(File file) throws IOException {
        Instance instance;

        try {
            instance = new ImageInstance(file);
        }
        catch (Exception e) {
            System.err.println("Wrong type");
            throw new IOException();
        }

        return instance;
    }

    @Deprecated
    private String getFileType(File file) {
        String mimetype = new MimetypesFileTypeMap().getContentType(file);
        return mimetype.split("/")[0];
    }
}
