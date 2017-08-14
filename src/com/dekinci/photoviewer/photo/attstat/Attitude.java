package com.dekinci.photoviewer.photo.attstat;

import java.util.Date;

abstract class Attitude {
    private Date dateCreated;

    public Attitude() {
        this.dateCreated = new Date();
    }

    public Attitude (Date creationDate) {
        this.dateCreated = creationDate;
    }

    public Date getDateCreated() {
        return new Date(dateCreated.getTime());
    }
}
