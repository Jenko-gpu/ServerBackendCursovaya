package org.jdev.service;

import org.jdev.entity.Scores;
import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.jdev.repository.ScoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoresRepository scoresRepository;

    @Autowired
    public ScoreService(ScoresRepository scoresRepository) {
        this.scoresRepository = scoresRepository;
    }

    public List<Scores> getScores(User user){
        return scoresRepository.findAllById_User(user);
    }
}
