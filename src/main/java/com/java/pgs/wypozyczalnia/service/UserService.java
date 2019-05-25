package com.java.pgs.wypozyczalnia.service;

import com.java.pgs.wypozyczalnia.domain.User;

public interface UserService {

    public User findUserByEmail(String email);
    public void saveUser(User user);
}
