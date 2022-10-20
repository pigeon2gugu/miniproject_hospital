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

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("add and select doing well")

    void addAndSelect() throws SQLException, ClassNotFoundException {
        UserDao userDao = context.getBean("awsUserDao", UserDao.class);

        userDao.deleteAll();

        User user1 = new User("1", "kyeonghwan", "1123");
        userDao.add(user1);

        User selectedUser = userDao.select(user1.getId());

        assertEquals(user1.getName(), selectedUser.getName());
        System.out.println(selectedUser.getName());

    }

    @Test
    @DisplayName("deleteAll and Count method test")
    void count() throws SQLException, ClassNotFoundException {
        User user1 = new User("1", "kyeonghwan", "1123");
        User user2 = new User("2", "sohyun", "1234");
        User user3 = new User("3", "sujin", "11423");

        UserDao userDao = context.getBean("awsUserDao", UserDao.class);
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());

        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());

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