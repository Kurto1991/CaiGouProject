package com.example.caigouapp.service;

import com.example.caigouapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /**
     * 根据用户信息生成token
     */
    @Test
    void getToken() {
        User user = userService.findUserByAccount("1");//获取一个用户

        String token = tokenService.getToken(user);//生成token

        System.out.println();
        System.out.println(token);
    }
}