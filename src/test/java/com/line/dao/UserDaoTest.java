package com.line.dao;

import com.line.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("add and select doing well")

    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        User user = new User("8", "EternityHwan", "123");
        //userDao.add(user);

        User selectedUser = userDao.select("8");
        Assertions.assertEquals("EternityHwan", selectedUser.getName());
        System.out.println(selectedUser.getName());
    }
}


    /*
    @Test
    @DisplayName("add and select doing well")
    void addAndSelect() throws SQLException, ClassNotFoundException {
        AWSUserDaoImpl userDao = new AWSUserDaoImpl();
        User user = new User("8", "EternityHwan", "123");
        //userDao.add(user);

        User selectedUser = userDao.select("8");
        Assertions.assertEquals("EternityHwan", selectedUser.getName());

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