package org.jdev.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScoresDTO {

    private Integer lectureScore;
    private Integer practiceScore;
    private Integer examScore;
    private String userName; // имя пользователя
    private String subjectName; // название предмета

    public ScoresDTO(Integer lectureScore, Integer practiceScore, Integer examScore, String userName, String subjectName) {
        this.lectureScore = lectureScore;
        this.practiceScore = practiceScore;
        this.examScore = examScore;
        this.userName = userName;
        this.subjectName = subjectName;
    }
}
