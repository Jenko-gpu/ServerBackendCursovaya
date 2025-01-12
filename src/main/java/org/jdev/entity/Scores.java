package org.jdev.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Scores {

    @EmbeddedId
    private ScoresId id;

    private Integer lecture_score;
    private Integer practice_score;
    private Integer exam_score;

    public Scores() {
    }

    public Scores(User user, Subject subject, Integer lecture_score, Integer practice_score, Integer exam_score) {
        this.id = new ScoresId(user, subject);
        this.lecture_score = lecture_score;
        this.practice_score = practice_score;
        this.exam_score = exam_score;
    }

    @Override
    public String toString() {
        return "Scores{" +
                ", lecture_score=" + lecture_score +
                ", practice_score=" + practice_score +
                ", exam_score=" + exam_score +
                '}';
    }
}