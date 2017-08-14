package com.dekinci.photoviewer.photo.attstat;

import java.util.Date;
import java.util.LinkedList;

public class AttStat {
    LinkedList<Attitude> stats;
    private int likeCounter;
    private int dislikeCounter;

    public AttStat() {
        this.likeCounter = 0;
        this.stats = new LinkedList<>();
    }

    public void like() {
        this.likeCounter++;
        this.stats.addLast(new Like());
    }

    public void dislike() {
        this.dislikeCounter++;
        this.stats.addLast(new Dislike());
    }

    public int getAtt() {
        return this.likeCounter - this.dislikeCounter;
    }

    public LinkedList<Date> getLikes() {
        LinkedList<Date> likesToReturn = new LinkedList<>();
        for (Attitude a: stats)
            if (a instanceof Like)
                likesToReturn.addLast(a.getDateChanged());
        return likesToReturn;
    }

    public LinkedList<Date> getDislikes() {
        LinkedList<Date> dislikesToReturn = new LinkedList<>();
        for (Attitude a: stats)
            if (a instanceof Dislike)
                dislikesToReturn.addLast(a.getDateChanged());
        return dislikesToReturn;
    }
}
