package com.yuryanat.restclient.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Set;

public class Role {
    int id;
    private String role;
    private Set<User> users;

    public Role() {
    }

    public Role(int id, String role, Set<User> users) {
        this.id = id;
        this.role = role;
        this.users = users;
    }

    public Role(String role) {
        this.role = role;
        this.users = new HashSet<>();
    }

    public Role(String role, Set<User> users) {
        this.role = role;
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
