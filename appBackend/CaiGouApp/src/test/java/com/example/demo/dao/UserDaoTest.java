package com.example.demo.dao;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserDaoTest {
    @Autowired
    private UserDao dao;
    @Test
    void findAll(){
        List<User> list = dao.findAll();
        for (User user: list) {
            System.out.println(user);

        }
    }
    @Test
    void findByTags(){
        User user = dao.findByTags("1");
        System.out.println(user);

    }

}