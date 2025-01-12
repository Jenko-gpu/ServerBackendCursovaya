package org.jdev.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Embeddable
@Setter
@Getter
public class ScoresId implements Serializable {

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "Subject_id")
    private Subject subject;

    public ScoresId() {}

    public ScoresId(User user, Subject subject) {
        this.user = user;
        this.subject = subject;
    }

}