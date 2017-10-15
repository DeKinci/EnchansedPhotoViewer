package photoviewer.model.core.files.unit.data.info.attstat;

import java.util.Date;

abstract class Attitude {
    private Date dateCreated;

    Attitude() {
        this.dateCreated = new Date();
    }

    Attitude(Date creationDate) {
        this.dateCreated = creationDate;
    }

    Date getDateCreated() {
        return new Date(dateCreated.getTime());
    }
}
