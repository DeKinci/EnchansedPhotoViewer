package main.java.photoviewer.core.unit.data.attstat;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class AttStat implements Serializable{
    private LinkedList<Attitude> stats;
    private int likeCounter;
    private int dislikeCounter;

    public AttStat() {
        init();
        this.stats = new LinkedList<>();
    }

    public AttStat(LinkedList<Date> likes, LinkedList<Date> dislikes) {
        init();
        for (Date d: likes) {
            this.likeCounter++;
            this.stats.add(new Like(d));
        }

        for (Date d: dislikes) {
            this.dislikeCounter++;
            this.stats.add(new Dislike(d));
        }
    }

    private void init() {
        this.likeCounter = 0;
        this.dislikeCounter = 0;
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
                likesToReturn.addLast(a.getDateCreated());
        return likesToReturn;
    }

    public LinkedList<Date> getDislikes() {
        LinkedList<Date> dislikesToReturn = new LinkedList<>();
        for (Attitude a: stats)
            if (a instanceof Dislike)
                dislikesToReturn.addLast(a.getDateCreated());
        return dislikesToReturn;
    }

    public LinkedList<Attitude> getAtts() {
        return new LinkedList<>(stats);
    }
}
