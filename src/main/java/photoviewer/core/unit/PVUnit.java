package main.java.photoviewer.core.unit;

import main.java.photoviewer.core.unit.data.attstat.AttStat;
import main.java.photoviewer.core.unit.data.content.ContentFactory;
import main.java.photoviewer.core.unit.data.content.ContentIO;
import main.java.photoviewer.core.unit.data.content.Content;
import main.java.photoviewer.core.unit.data.rating.Rating;
import main.java.photoviewer.core.unit.data.tagging.Tags;

import java.io.*;
import java.util.Date;
import java.util.LinkedList;

public abstract class PVUnit implements Serializable {
    private transient ContentFactory contentFactory = ContentFactory.makeContentFactory();
    transient protected Content content;

    private String unitName;
    private Rating unitRating;
    private Tags unitTags;
    private AttStat unitStats;

    public PVUnit(File source) throws IOException {
        this.content = contentFactory.createContent(ContentIO.read(source));
        this.unitName = source.getName().substring(0, source.getName().lastIndexOf("."));
        this.unitRating = new Rating();
        this.unitTags = new Tags();
        this.unitStats = new AttStat();
    }

    public PVUnit(PVUnit source) {
        this.content = contentFactory.createContent(source.getContent());
        this.unitName = source.toString();

        try {
            this.unitRating = new Rating(source.getRating());
        } catch (Rating.NoRatingException | Rating.OutOfRatingException e) {
            this.unitRating = new Rating();
        }

        try {
            this.unitTags = new Tags(source.getTags());
        } catch (Tags.EmptyTagsException e) {
            this.unitTags = new Tags();
        }

        this.unitStats = new AttStat(source.getLikes(), source.getDislikes());
    }

    public void saveFile(File outputFile) throws IOException {
        FileOutputStream fos = new FileOutputStream(outputFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }

    public void rename(String newName) {
        this.unitName = newName;
    }

    public int getRating() throws Rating.NoRatingException {
        return this.unitRating.getRating();
    }

    public boolean hasRating() {
        return this.unitRating.hasRating();
    }

    public void setRating(int newRating) throws Rating.OutOfRatingException {
        this.unitRating.setRating(newRating);
    }

    public void addTag(String newTag) throws Tags.TagExistsException {
        this.unitTags.addTeg(newTag);
    }

    public void removeTag(String removingName) throws Tags.TagNotFoundException {
        this.unitTags.removeTag(removingName);
    }

    public void clearTags() {
        this.unitTags.clearTags();
    }

    public boolean hasTags() {
        return this.unitTags.hasTags();
    }

    public LinkedList<String> getTags() throws Tags.EmptyTagsException {
        return this.unitTags.getTags();
    }

    public int getAtt() {
        return this.unitStats.getAtt();
    }

    public LinkedList<Date> getLikes() {
        return unitStats.getLikes();
    }

    public LinkedList<Date> getDislikes() {
        return unitStats.getDislikes();
    }

    public Object getContent() {
        return content.getContent();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ContentIO.write(content, out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        content = ContentIO.read(in);
    }

    @Override
    public String toString() {
        return this.unitName;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(this.toString());//TODO: Check images for equality
    }
}
