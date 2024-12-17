package org.jdev.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jdev.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


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

    public User findById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.find(User.class, id);
        session.close();
        return user;
    }
}
