package org.jdev.service;

import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.jdev.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubject(User user){
        return subjectRepository.getByUser(user);
    }
}
