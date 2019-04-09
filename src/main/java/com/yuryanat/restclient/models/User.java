package com.yuryanat.restclient.models;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private Integer id;
    private String login;
    private String password;
    private String name;
    private String email;
    private Set<Role> roles;

    public User(){
    }

    public User(String login, String password, String name, String email, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
    public User(String login, String password, String name, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(int id, String login, String password, String name, String email, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
    public User(int id, String login, String password, String name, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return new LinkedHashSet<>(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(roles,this.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, email, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}