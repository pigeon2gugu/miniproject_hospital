package com.line.dao;

import com.line.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserDaoFactory.class)
class UserDaoTest {

    @Autowired
    ApplicationContext context;
    UserDao userDao;
    User user1;
    User user2;
    User user3;

    @BeforeEach
    void setUp() throws SQLException {
        userDao = context.getBean("awsUserDao", UserDao.class);

        userDao.deleteAll();

        user1 = new User("1", "kyeonghwan", "1123");
        user2 = new User("2", "sohyun", "1234");
        user3 = new User("3", "sujin", "11423");
    }


    @Test
    @DisplayName("add and select doing well")

    void addAndSelect() throws SQLException, ClassNotFoundException {

        User user1 = new User("1", "kyeonghwan", "1123");
        userDao.add(user1);

        User selectedUser = userDao.select(user1.getId());

        assertEquals(user1.getName(), selectedUser.getName());
        System.out.println(selectedUser.getName());

    }

    @Test
    @DisplayName("deleteAll and Count method test")
    void count() throws SQLException, ClassNotFoundException {

        userDao.add(user1);
        assertEquals(1, userDao.getCount());
        userDao.add(user2);
        assertEquals(2, userDao.getCount());
        userDao.add(user3);
        assertEquals(3, userDao.getCount());

    }

    @Test
    void select() throws SQLException, ClassNotFoundException {
        assertThrows(EmptyResultDataAccessException.class, ()-> {
            userDao.select("30");
        });


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

