package com.line.dao;

//jdbcContextWithStatementStrategy는 다른 Dao 예를들어 HospitalDao에서도 쓸 수  있어  UserDao class에서 분리

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    private DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeSql(String sql) throws SQLException{
        //deleteAllStrategy라는 이름이 있지만 인터페이스 구현체인 익명 클래스는 이름이 없음.
        this.workContextWithStatementStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makePreparedStatement(Connection connection) throws SQLException {
                return connection.prepareStatement(sql);
            }
        });
    }
    public void workContextWithStatementStrategy(StatementStrategy stmt) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //conn = connectionMaker.makeConnection();
            conn = dataSource.getConnection();
            ps = stmt.makePreparedStatement(conn);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { //error가 나도 실행되는 블럭
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }

            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }

            }
        }
    }
}
