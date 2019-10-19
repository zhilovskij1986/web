package com.zhilovskij.dao.impl;

import com.zhilovskij.dao.UserDAO;
import com.zhilovskij.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateUserDAO implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session(){
        return sessionFactory.openSession ();
    }

    @Override
    public List<User> getAll() {
        return session ().createQuery ("from User", User.class).list ();
    }

    @Override
    public User getOne(String email) {
        Query<User> q = session().createQuery(
                "from User where email = :email", User.class);
        q.setParameter("email", email);
        return q.list().stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {
        session().save(user);
    }
}
