package com.dekinci.photoviewer.photo.attstat;

import java.util.Date;

abstract class Attitude {
    private Date dateChanged;

    public Attitude() {
        dateChanged = new Date();
    }

    public Date getDateChanged() {
        return new Date(dateChanged.getTime());
    }
}
