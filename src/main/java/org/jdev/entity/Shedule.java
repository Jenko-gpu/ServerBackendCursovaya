package org.jdev.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Setter
@Getter
@Entity
public class Shedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    //@MapsId("subjectId")
    @JoinColumn(name = "Subject_id")
    private Subject subject;

    private Date date;

    private LocalTime startTime; // Время начала урока

    private LocalTime endTime; // Время окончания урока

    private Integer typeOfLesson; // Тип урока 0 - лекция, 1 - практическое занятие, 2 - лабараторная работа, 3 - зачёт, 4 - экзамен

    public Shedule(){
    }

    public Shedule(LocalTime startTime,LocalTime endTime, Date date, Subject subject, Integer typeOfLesson) {
        this.endTime = endTime;
        this.startTime = startTime;
        this.date = date;
        this.subject = subject;
        this.typeOfLesson = typeOfLesson;
    }

}


