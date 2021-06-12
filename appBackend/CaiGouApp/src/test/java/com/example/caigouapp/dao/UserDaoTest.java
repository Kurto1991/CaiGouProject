package com.example.caigouapp.dao;

import com.example.caigouapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    void countAll() {
        int userNum = userDao.countAll();

        int i = 0;
        while (i < userNum){
            List<User> userList = userDao.findAllUser(i);
            for(User user:userList){
                System.out.println(user.toString());
            }
            i+=10;
            System.out.println("--end--");
        }


    }
}