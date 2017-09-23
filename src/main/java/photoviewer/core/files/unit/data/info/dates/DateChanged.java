package main.java.photoviewer.core.files.unit.data.info.dates;

import java.util.Date;

public abstract class DateChanged {
    private Date date;

    public DateChanged() {
        date = new Date();
    }

    public DateChanged(Date dateCreated) {
        date = new Date(dateCreated.getTime());
    }

    public Date getDate() {
        return date;
    }
}
