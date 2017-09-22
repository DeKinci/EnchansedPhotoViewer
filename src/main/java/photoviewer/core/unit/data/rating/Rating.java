package main.java.photoviewer.core.unit.data.rating;

import java.io.Serializable;

public class Rating implements Serializable{
    private int rating;
    private boolean hasRating;

    public Rating() {
        this.hasRating = false;
    }

    public Rating(int newRating) throws OutOfRatingException {
        this.hasRating = true;
        setRating(newRating);
    }

    public void setRating(int newRating) throws OutOfRatingException {
        if (newRating >= 0 && newRating <= 11) {
            this.hasRating = true;
            this.rating = newRating;
        }
        else
            throw new OutOfRatingException();
    }

    public void removeRating() {
        this.hasRating = false;
    }

    public int getRating() throws NoRatingException {
        if (this.hasRating)
            return this.rating;
        else
            throw new NoRatingException();
    }

    public boolean hasRating() {
        return this.hasRating;
    }

    public class NoRatingException extends Exception{
        private NoRatingException() {
            super("No rating");
        }
    }

    public class OutOfRatingException extends Exception{
        private OutOfRatingException() {
            super("Rating value is too big or too small");
        }
    }
}
