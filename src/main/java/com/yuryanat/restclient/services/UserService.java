package com.yuryanat.restclient.services;

import com.yuryanat.restclient.models.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService {
    void addNewUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    List<User> getAllUsers();
    User getUserByID(int id);
    User getUserByLogin(String login);
}
