package photoviewer.core.files.unit.factory;

import photoviewer.core.files.image.PVImage;
import photoviewer.core.files.unit.PVUnit;

import java.io.*;

public class PVImageFactory implements PVUnitFactory {
    private volatile static PVImageFactory imageFactory;

    public static PVImageFactory getInstance() {
        if (imageFactory == null)
            synchronized (PVImageFactory.class) {
                if (imageFactory == null)
                    imageFactory = new PVImageFactory();
            }

        return imageFactory;
    }

    private PVImageFactory() {
    }

    @Override
    public PVUnit openFile(File source) throws IOException {
        PVImage image;

        try {
            FileInputStream fis = new FileInputStream(source);
            ObjectInputStream oin = new ObjectInputStream(fis);
            image = new PVImage((PVImage) oin.readObject());
        }
        catch (StreamCorruptedException | ClassNotFoundException e) {
            image = new PVImage(source);
        }

        return image;
    }
}
