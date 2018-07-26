package com.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private int user_group_id;

    public User(String username, String password, String email, int user_group_id) {
        this.username = username;
        setPassword(password);
        this.email = email;
        this.user_group_id = user_group_id;
    }

    public User() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    public String getPassword() {
        return password;
    }
}