package org.jdev.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jdev.entity.Scores;
import org.jdev.entity.Subject;
import org.jdev.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class AccountDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User save(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public Subject save(Subject subject){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(subject);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return subject;
    }

    public Scores save(Scores score){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(score);
        session.flush();
        session.getTransaction().commit();
        session.close();
        return score;
    }

    public List<Scores> getAllScores(User user){
        Session session = sessionFactory.openSession();
        List<Scores> scoresList = null;
        try {
            String hql = "FROM Scores s WHERE s.user = :user";
            Query<Scores> query = session.createQuery(hql, Scores.class);
            query.setParameter("user", user);
            scoresList = query.getResultList();
        } finally {
            session.close();
        }
        return scoresList;
    }
    }


    public User findById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.find(User.class, id);
        session.close();
        return user;
    }
}
