package com.dekinci.photoviewer.photo.rating;

import java.io.Serializable;

public class Rating implements Serializable{
    private int rating;
    private boolean hasRating;

    public Rating() {
        this.hasRating = false;
    }

    public Rating(int newRating) {

        this.hasRating = true;

    }

    public void setRating(int newRating) throws OutOfRatingExceptions{
        if (newRating >= 0 && newRating <= 11) {
            this.hasRating = true;
            this.rating = newRating;
        }
        else
            throw new OutOfRatingExceptions();
    }

    public void removeRating() {
        this.hasRating = false;
    }

    public int getRating() throws NoRatingExceptions {
        if (this.hasRating)
            return this.rating;
        else
            throw new NoRatingExceptions();
    }

    public boolean hasRating() {
        return this.hasRating;
    }

    public class NoRatingExceptions extends Exception{
        private NoRatingExceptions() {
            super("No rating");
        }
    }

    public class OutOfRatingExceptions extends Exception{
        private OutOfRatingExceptions() {
            super("Rating value is too big or too small");
        }
    }
}
