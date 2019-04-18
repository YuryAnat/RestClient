package com.yuryanat.restclient.repositories;

import com.yuryanat.restclient.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository{
    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User getUserById(int id);

    User getUserByLogin(String name);

    List<User> getAllUsers();
}
