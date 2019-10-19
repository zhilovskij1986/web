package com.zhilovskij.dao.impl;

import com.zhilovskij.dao.UserDAO;
import com.zhilovskij.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class JdbcTemplUserDAO implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query ("select * from users",
                new BeanPropertyRowMapper<> (User.class));
    }

    @Override
    public User getOne(String email) {
        return  jdbcTemplate.query ("select from users where email=?",
                new Object[]{email},
                new BeanPropertyRowMapper<> (User.class)
        ).stream ().findAny ().orElse (null);
    }

        @Override
        public void add (User user){
        jdbcTemplate.update ("select into users values (?,?,?)",
        user.getName (), user.getSurname (), user.getEmail ());

    }
}


