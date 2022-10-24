package com.line.dao;


import com.line.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class UserDao {

    //private ConnectionMaker connectionMaker;
    //private final DataSource dataSource; // DataSource를 의존하게
    //private final JdbcContext jdbcContext;
    private JdbcTemplate jdbcTemplate;


/*    public UserDao() {
        this.connectionMaker = new AWSConnectionMaker();
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }*/

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource; //datasource 생성자
        this.jdbcContext = new JdbcContext(dataSource);
    }
    */



    /*
    delete from users가 table명이 들어 있기 때문에 deleteAll()에서만 쓴다.
    클래스를 자꾸 만들면 많아져 이렇게 한번만 쓰는 경우는 내부 클래스를 쓴다.

    public void deleteAll() throws SQLException {
        jdbcContextWithStatementStrategy(new DeleteAllSttrategy());

    public void add(User user) throws SQLException, ClassNotFoundException {
        AddStrategy addStrategy = new AddStrategy(user);
        jdbcContextWithStatementStrategy(addStrategy);
    }
    */


    public void deleteAll() throws SQLException {
        //this.jdbcContext.executeSql("delete from users");
        this.jdbcTemplate.update("delete from users");
    }

    public void add(final User user) throws SQLException {
        this.jdbcTemplate.update("insert into users(id, name, password) values (?, ?, ?);",
                user.getId(), user.getName(), user.getPassword());
    }

    public int getCount() throws SQLException {
        //queryForObject에 두번째 parameter로 Integer.class를 넘겨줌으로써 int형의 데이터를 받아옴
        return this.jdbcTemplate.queryForObject("select count(*) from users;", Integer.class);
    }

    public User select(String id) {
        String sql = "select * from users where id = ?";

        //RowMapper : Interface 구현체로 ResultSet의 정보를 User에 매핑시 사용
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<User> getAll() {
        String sql = "select * from users order by id";
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User(rs.getString("id"), rs.getString("name"),
                        rs.getString("password"));
                return user;
            }
        };
        return this.jdbcTemplate.query(sql, rowMapper);
    }


    /*
    public void add(User user) throws SQLException, ClassNotFoundException {
        jdbcContext.workContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = null;
                pstmt = connection.prepareStatement("INSERT INTO users(id, name, password) VALUES(?,?,?);");
                pstmt.setString(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getPassword());
                return pstmt;
            }
        });

    }
        public User select(String id) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();

        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection conn = connectionMaker.makeConnection();
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));
        }

        rs.close();
        ps.close();
        conn.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

        public int getCount() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //conn = connectionMaker.makeConnection();
            conn = dataSource.getConnection();
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
    */

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Map<String, String> env = System.getenv();
    }
}
