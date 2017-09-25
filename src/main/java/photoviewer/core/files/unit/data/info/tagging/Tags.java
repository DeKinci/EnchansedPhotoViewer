package photoviewer.core.files.unit.data.info.tagging;

import java.io.Serializable;
import java.util.LinkedList;

public class Tags implements Serializable{
    private LinkedList<Tag> tags;
    private boolean hasTags = false;
    private GrandTagListOfAllTimes grandTagList;

    public Tags() {
        init();
    }

    public Tags(LinkedList<Tag> newTags) {
        init();

        if (!newTags.isEmpty()) {
            hasTags = true;
            for (Tag tag : newTags)
                this.tags.addLast(tag);
        }
    }

    private void init() {
        this.tags = new LinkedList<>();
        grandTagList = GrandTagListOfAllTimes.getInstance();
    }

    public void addTeg(Tag newTag) throws TagExistsException {
        if (this.tags.contains(newTag))
            throw new TagExistsException(newTag.toString());
        else {
            if (!this.grandTagList.getTagList().contains(newTag))
                this.grandTagList.getTagList().add(newTag);

            this.tags.addLast(newTag);
            this.hasTags = true;
        }
    }

    public void addTegByName(String newTagName) throws TagExistsException {
        addTeg(new Tag(newTagName));
    }

    public void removeTag(Tag removingTag) throws TagNotFoundException {
        if (this.tags.contains(removingTag)) {
            this.tags.remove(removingTag);
            if (this.tags.isEmpty())
                this.hasTags = false;
        }
        else
            throw new TagNotFoundException(removingTag.toString());
    }

    public void removeTagByName(String tagName) throws TagNotFoundException {
        removeTag(new Tag(tagName));
    }

    public void clearTags() {
        this.tags.clear();
        this.hasTags = false;
    }

    public LinkedList<Tag> getTags() {
        return this.tags;
    }

    public boolean hasTags() {
        return this.hasTags;
    }

    public class TagNotFoundException extends Exception {
        private TagNotFoundException(String tagName) {
            super("Tag " + tagName + " is not found");
        }
    }

    public class TagExistsException extends Exception {
        private TagExistsException(String tagName) {
            super("Tag " + tagName + " already exists");
        }
    }

    public class EmptyTagsException extends Exception {
        private EmptyTagsException() {
            super("There is no tags");
        }
    }
}
