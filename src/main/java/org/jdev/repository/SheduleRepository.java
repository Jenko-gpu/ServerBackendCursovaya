package org.jdev.repository;

import org.jdev.entity.Shedule;
import org.jdev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Date;
import java.util.List;

public interface SheduleRepository extends JpaRepository<Shedule, Integer> {

    @Query("SELECT s FROM Shedule s WHERE s.subject.id IN " +
            "(SELECT sc.id.subject.id FROM Scores sc WHERE sc.id.user = :user) " +
            "AND s.date BETWEEN :startDate AND :endDate")
    List<Shedule> findAllByUserIdAndDateRange(@Param("user") User user,
                                              @Param("startDate") Date startDate,
                                              @Param("endDate") Date endDate);
}
