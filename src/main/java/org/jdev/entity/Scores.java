package org.jdev.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class Scores {

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Subject_id")
    private Subject subject;

    private Integer lecture_score;

    private Integer practice_score;

    private Integer exam_score;


    public Scores() {

    }

    public Scores(User user, Subject subject, Integer lecture_score, Integer practice_score, Integer exam_score) {
        this.user = user;
        this.subject = subject;
        this.lecture_score = lecture_score;
        this.practice_score = practice_score;
        this.exam_score = exam_score;
    }

    @Override
    public String toString() {
        return "Scores{" +
                "user=" + user +
                ", subject=" + subject +
                ", lecture_score=" + lecture_score +
                ", practice_score=" + practice_score +
                ", exam_score=" + exam_score +
                '}';
    }

}
