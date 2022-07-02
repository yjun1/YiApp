package com.example.yiapp.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport{

    private int id;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
