package main.java.photoviewer.core.files.unit.data.tagging;

import java.io.Serializable;
import java.util.LinkedList;

public class Tags implements Serializable{
    private LinkedList<String> tags = new LinkedList<>();
    private boolean hasTags = false;

    public Tags() { }

    public Tags(LinkedList<String> newTags) {
        if (!newTags.isEmpty()) {
            hasTags = true;
            for (String tag : newTags)
                this.tags.addLast(tag);
        }
    }

    public void addTeg(String newTag) throws TagExistsException {
        if (this.tags.contains(newTag))
            throw new TagExistsException();
        else {
            this.tags.addLast(newTag);
            this.hasTags = true;
        }
    }

    public void removeTag(String removingTag) throws TagNotFoundException {
        if (this.tags.contains(removingTag)) {
            this.tags.remove(removingTag);
            if (this.tags.isEmpty())
                this.hasTags = false;
        }
        else
            throw new TagNotFoundException();
    }

    public void clearTags() {
        this.tags.clear();
        this.hasTags = false;
    }

    public LinkedList<String> getTags() throws EmptyTagsException {
        if (hasTags)
            return new LinkedList<>(tags);
        else throw new EmptyTagsException();
    }

    public boolean hasTags() {
        return hasTags;
    }

    public class TagNotFoundException extends Exception {
        private TagNotFoundException() {
            super("Tag is not found");
        }
    }

    public class TagExistsException extends Exception {
        private TagExistsException() {
            super("Tag already exists");
        }
    }

    public class EmptyTagsException extends Exception {
        private EmptyTagsException() {
            super("There is no tags");
        }
    }
}
