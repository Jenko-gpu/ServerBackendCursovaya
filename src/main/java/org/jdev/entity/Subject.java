package org.jdev.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Subject {

    @Id
    private Integer id;

    private String name;

    private String teacher;


    public Subject() {
    }

    public Subject(Integer id, String name, String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
    }

    public Subject(Subject subject) {
        this.id = subject.id;
        this.name = subject.name;
        this.teacher = subject.teacher;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", curs='" + teacher + '\'' +
                '}';
    }
}
