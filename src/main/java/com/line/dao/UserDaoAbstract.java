package com.line.dao;


import com.line.domain.User;

import java.sql.*;
import java.util.Map;

public abstract class UserDaoAbstract {

    public abstract Connection makeConnection() throws SQLException;

    public void add(User user) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = makeConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate(); //ctrl + enter
        ps.close();
        conn.close();
    }

    public User select(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        rs.close();
        ps.close();
        conn.close();
        return user;
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();

        AWSUserDaoImpl userDao2 = new AWSUserDaoImpl();
        User user = new User("8", "EternityHwan", "1123");
        //userDao2.add(user);
        User user2 = userDao2.select("4");

        System.out.println(user2.getName());
    }
}
