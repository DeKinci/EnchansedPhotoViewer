package photoviewer.model.core.files.unit.data.info.tagging;

public class Tag {
    private String tag;

    public Tag(String name) {
        this.tag = name;
    }

    @Override
    public String toString() {
        return this.tag;
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }
}
