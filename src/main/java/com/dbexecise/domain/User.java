package com.dbexecise.domain;

import java.sql.*;
import java.util.Map;

public class User {
    public String getID;
    private String id;
    private String name;
    private String password;

    public User() {
    }

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getID() {
        return id;
    }
}
