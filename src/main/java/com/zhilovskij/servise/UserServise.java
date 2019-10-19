package com.zhilovskij.servise;

import com.zhilovskij.model.User;

import java.util.List;

public interface UserServise {
    List<User> getAll();

    User getOne(String email);

    void add(User user);

}
