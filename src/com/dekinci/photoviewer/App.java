package com.dekinci.photoviewer;

import com.dekinci.photoviewer.unit.PVUnit;
import com.dekinci.photoviewer.unit.factory.PVImageFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {
    private PVUnit testImage;
    private PVImageFactory factory;
    final private String fileExtension = ".epvi";

    public static void main(String... args) throws IOException {
        App app = new App();
        app.factory = PVImageFactory.makeContentFactory();

        app.importImage();
        app.saveImage();

        app.openImage();
        app.exportImage();
    }

    private void importImage() {
        File img = new File("misc/img.jpg");
        try {
            testImage = factory.importFile(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImage() {
        File out = new File("misc/img" + fileExtension);

        try {
            testImage.saveFile(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openImage() {
        File in = new File("misc/img" + fileExtension);
        try {
            testImage = factory.openFile(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportImage() throws IOException {
        ImageIO.write((BufferedImage) testImage.getContent(), "PNG", new File("misc/out.png"));
    }
}
