package org.jdev.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SheduleDTO {

    private String subjectName;
    private String Date;
    private String startTime;
    private String endTime;
    private String teachersName;

    public SheduleDTO(String subjectName, String date, String startTime, String endTime, String teachersName) {
        this.subjectName = subjectName;
        Date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.teachersName = teachersName;
    }
}
