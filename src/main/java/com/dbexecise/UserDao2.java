package com.dbexecise;

import com.dbexecise.domain.User;

import java.sql.*;
import java.util.Map;

public class UserDao2 {
    public void add() throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES(?, ?, ?)");
        ps.setString(1, "4");
        ps.setString(2, "Haneul");
        ps.setString(3, "1123");

        ps.executeUpdate(); //ctrl + enter
        ps.close();
        conn.close();
    }

    public User select(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(dbHost, dbUser, dbPassword);
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
        UserDao2 userDao2 = new UserDao2();
        //userDao2.add();
        User user = userDao2.select("1");

        System.out.println(user.getName());
    }
}
