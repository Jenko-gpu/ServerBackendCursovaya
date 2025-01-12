package org.jdev.repository;

import org.jdev.entity.Scores;
import org.jdev.entity.ScoresId;
import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoresRepository extends JpaRepository<Scores, ScoresId> {
    @Query("SELECT score FROM Scores score WHERE score.id.user = :user")
    List<Scores> findAllById_User(@Param("user") User user);

}


