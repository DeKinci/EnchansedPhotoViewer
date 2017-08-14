package com.dekinci.photoviewer.photo;

import com.dekinci.photoviewer.photo.attstat.AttStat;
import com.dekinci.photoviewer.photo.rating.Rating;
import com.dekinci.photoviewer.photo.tagging.Tags;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.LinkedList;

public class PVImage implements Serializable {
    transient private BufferedImage image;

    private String imgName;
    private Rating imgRating;
    private Tags imgTags;
    private AttStat imgStats;

    public PVImage(File source) throws IOException, Rating.OutOfRatingExceptions {
        this.image = ImageIO.read(source);
        this.imgName = source.getName().substring(0, source.getName().lastIndexOf("."));
        this.imgRating = new Rating();
        this.imgTags = new Tags();
        this.imgStats = new AttStat();
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

    public void addTeg(String newTag) throws Tags.TagExistsException {
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
