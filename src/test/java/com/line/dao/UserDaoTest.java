package com.line.dao;

import com.line.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class UserDaoTest {

    /*
    @Test
    @DisplayName("add and select doing well")
    void addAndSelect() throws SQLException, ClassNotFoundException {
        AWSUserDaoImpl userDao = new AWSUserDaoImpl();
        User user = new User("8", "EternityHwan", "123");
        //userDao.add(user);

        User selectedUser = userDao.select("8");
        Assertions.assertEquals("EternityHwan", selectedUser.getName());


     */
    @Test
    @DisplayName("add and select doing well")

    /*
    Factory 적용 전
    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
        User user = new User("8", "EternityHwan", "123");
        //userDao.add(user);

        User selectedUser = userDao.select("8");
        Assertions.assertEquals("EternityHwan", selectedUser.getName());
        System.out.println(selectedUser.getName());


    }
     */
    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDaoFactory().awsUserDao();
        User user = new User("8", "EternityHwan", "123");
        //userDao.add(user);

        User selectedUser = userDao.select("8");
        Assertions.assertEquals("EternityHwan", selectedUser.getName());
        System.out.println(selectedUser.getName());
    }
}