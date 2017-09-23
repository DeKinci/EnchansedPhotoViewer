package main.java.photoviewer.core.files.unit.data.content;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContentIO {
    private static ContentFactory contentFactory = ContentFactory.makeContentFactory();

    public static void write(Content content, ObjectOutputStream objectOutputStream) throws IOException {
        ImageIO.write((BufferedImage) content.getContent(), "png", objectOutputStream);
    }

    public static Content read(ObjectInputStream objectInputStream) throws IOException {
        return contentFactory.createContent(ImageIO.read(objectInputStream));
    }

    public static Content read(File file) throws IOException {
        return contentFactory.createContent(ImageIO.read(file));
    }
}
