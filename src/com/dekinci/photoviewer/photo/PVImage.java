package com.dekinci.photoviewer.photo;

import com.dekinci.photoviewer.photo.attstat.AttStat;
import com.dekinci.photoviewer.photo.rating.Rating;
import com.dekinci.photoviewer.photo.tagging.Tags;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Date;
import java.util.LinkedList;

public class PVImage implements Serializable {
    transient private BufferedImage image;

    private String imgName;
    private Rating imgRating;
    private Tags imgTags;
    private AttStat imgStats;

    public static PVImage importFile(File source) throws IOException, Rating.OutOfRatingExceptions {
        return new PVImage(source);
    }

    public static PVImage openFile(File source) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(source);
        ObjectInputStream oin = new ObjectInputStream(fis);
        return new PVImage((PVImage) oin.readObject());
    }

    public void saveFile(File outputFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(outputFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    private PVImage(File source) throws IOException {
        this.image = ImageIO.read(source);
        this.imgName = source.getName().substring(0, source.getName().lastIndexOf("."));
        this.imgRating = new Rating();
        this.imgTags = new Tags();
        this.imgStats = new AttStat();
    }

    private PVImage (PVImage source) {
        this.image = source.getBufferedImage();
        this.imgName = source.getName();

        try {
            this.imgRating = new Rating(source.getRating());
        } catch (Rating.NoRatingExceptions noRatingExceptions) {
            this.imgRating = new Rating();
        }

        try {
            this.imgTags = new Tags(source.getTags());
        } catch (Tags.EmptyTagsException e) {
            this.imgTags = new Tags();
        }

        this.imgStats = new AttStat(source.getLikes(), source.getDislikes());
    }

    public String getName() {
        return this.imgName;
    }

    public void rename(String newName) {
        this.imgName = newName;
    }

    public int getRating() throws Rating.NoRatingExceptions {
        return this.imgRating.getRating();
    }

    public boolean hasRating() {
        return this.imgRating.hasRating();
    }

    public void setRating(int newRating) throws Rating.OutOfRatingExceptions {
        this.imgRating.setRating(newRating);
    }

    public void addTag(String newTag) throws Tags.TagExistsException {
        this.imgTags.addTeg(newTag);
    }

    public void removeTag(String removingName) throws Tags.TagNotFoundException {
        this.imgTags.removeTag(removingName);
    }

    public void clearTags() {
        this.imgTags.clearTags();
    }

    public boolean hasTags() {
        return this.imgTags.hasTags();
    }

    public LinkedList<String> getTags() throws Tags.EmptyTagsException {
        return this.imgTags.getTags();
    }

    public int getAtt() {
        return this.imgStats.getAtt();
    }

    public LinkedList<Date> getLikes() {
        return imgStats.getLikes();
    }

    public LinkedList<Date> getDislikes() {
        return imgStats.getDislikes();
    }

    public BufferedImage getBufferedImage() {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(image, "png", out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        image = ImageIO.read(in);
    }
}
