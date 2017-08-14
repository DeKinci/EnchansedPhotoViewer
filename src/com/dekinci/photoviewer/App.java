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
        String[] test = "test".split(" ");

        try {
            testImage = new PVImage(img);
            FileOutputStream fos = new FileOutputStream("misc/img.pvi");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(testImage);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Rating.OutOfRatingExceptions outOfRatingExceptions) {
            outOfRatingExceptions.printStackTrace();
        }
    }

    public void deserialize() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("misc/img.pvi");
            ObjectInputStream oin = new ObjectInputStream(fis);
            testImage = (PVImage) oin.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeImage() throws IOException {
        ImageIO.write(testImage.getBufferedImage(), "PNG", new File("misc/out.png"));
    }
}
