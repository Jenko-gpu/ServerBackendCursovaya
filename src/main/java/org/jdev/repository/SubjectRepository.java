package org.jdev.repository;

import org.jdev.entity.Scores;
import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query("SELECT s.id.subject FROM Scores s WHERE s.id.user = :user")
    List<Subject> getByUser(@Param("user") User user);
}

