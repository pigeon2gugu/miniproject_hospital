package com.line.dao;


import com.line.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.*;
import java.util.Map;

public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao() {
        this.connectionMaker = new AWSConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = connectionMaker.makeConnection();
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
        Connection conn = connectionMaker.makeConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1,id);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        }

        rs.close();
        ps.close();
        conn.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = connectionMaker.makeConnection();
            ps = new DeleteAllSttrategy().makePreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { //error가 나도 실행되는 블럭
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }

            }

            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }

            }
        }


    }

    public int getCount() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connectionMaker.makeConnection();
            ps = conn.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }

            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }

            }
        }
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();

        UserDao userDao2 = new UserDao();
        User user = new User("8", "EternityHwan", "1123");
        //userDao2.add(user);
        //User user = userDao2.select("1");

        System.out.println(user.getName());
    }
}
