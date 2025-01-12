package org.jdev.service;

import org.jdev.entity.Shedule;
import org.jdev.entity.User;
import org.jdev.repository.SheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SheduleService {
    private final SheduleRepository sheduleRepository;

    @Autowired
    public SheduleService(SheduleRepository sheduleRepository) {
        this.sheduleRepository = sheduleRepository;
    }

    public List<Shedule> getShedule(User user, String startDay, String endDay) {
        return sheduleRepository.findAllByUserIdAndDateRange(user, Date.valueOf(startDay), Date.valueOf(endDay));
    }
}
