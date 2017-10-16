package photoviewer.entity.model.core.files.unit.data.info.name;

import java.io.Serializable;

public class Name implements Serializable {
    private String name;

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
