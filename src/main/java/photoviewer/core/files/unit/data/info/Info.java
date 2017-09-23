package main.java.photoviewer.core.files.unit.data.info;

import main.java.photoviewer.core.files.unit.data.info.attstat.AttStat;
import main.java.photoviewer.core.files.unit.data.info.dates.DateCreated;
import main.java.photoviewer.core.files.unit.data.info.dates.DateModified;
import main.java.photoviewer.core.files.unit.data.info.dates.DateOpened;
import main.java.photoviewer.core.files.unit.data.info.name.Name;
import main.java.photoviewer.core.files.unit.data.info.rating.Rating;
import main.java.photoviewer.core.files.unit.data.info.tagging.Tags;

public class Info {
    private Rating rating;
    private Tags tags;
    private AttStat attStat;
    private Name name;
    private DateCreated dateCreated;
    private DateModified dateModified;
    private DateOpened dateOpened;

    public Info(String name) {
        this.name = new Name(name);
        this.rating = new Rating();
        this.tags = new Tags();
        this.attStat = new AttStat();
        this.dateCreated = new DateCreated();
        this.dateModified = new DateModified();
        this.dateOpened = new DateOpened();
    }

    public Info(Info info) {
        this.rating = info.getRating();
        this.tags = info.getTags();
        this.attStat = info.getAttStat();
        this.name = info.getName();
        this.dateCreated = info.getDateCreated();
        this.dateModified = info.getDateModified();
        this.dateOpened = new DateOpened();
    }

    public Rating getRating() {
        return rating;
    }

    public Tags getTags() {
        return tags;
    }

    public AttStat getAttStat() {
        return attStat;
    }

    public Name getName() {
        return name;
    }

    public DateCreated getDateCreated() {
        return dateCreated;
    }

    public DateModified getDateModified() {
        return dateModified;
    }

    public DateOpened getDateOpened() {
        return dateOpened;
    }
}
