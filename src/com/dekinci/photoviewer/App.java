package com.dekinci.photoviewer;

import com.dekinci.photoviewer.photo.PVImage;
import com.dekinci.photoviewer.photo.rating.Rating;


import javax.imageio.ImageIO;
import java.io.*;

public class App {
    private PVImage testImage;

    public static void main(String... args) throws IOException {
        App app = new App();

        //app.serialize();

        app.deserialize();
        app.writeImage();
    }

    public void serialize() {
        File img = new File("misc/img.jpg");
        File out = new File("misc/img.pvi");

        try {
            testImage = PVImage.importFile(img);
            testImage.saveFile(out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Rating.OutOfRatingExceptions outOfRatingExceptions) {
            outOfRatingExceptions.printStackTrace();
        }
    }

    public void deserialize() {
        File in = new File("misc/img.pvi");
        try {
            testImage = PVImage.openFile(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeImage() throws IOException {
        ImageIO.write(testImage.getBufferedImage(), "PNG", new File("misc/out.png"));
    }
}
