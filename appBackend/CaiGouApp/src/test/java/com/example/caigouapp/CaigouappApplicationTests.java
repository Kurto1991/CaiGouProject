package com.example.caigouapp;

import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.entity.MenuInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CaigouappApplicationTests {

    @Autowired
    MenuDao menuDao;

    @Test
    void contextLoads() {
        String strings = menuDao.menu_food_info(4);
        strings.split("");

        System.out.println(strings);
    }
    @Test
     void getMenuinfo(){
          menuDao.findAll();
        System.out.println(menuDao.findAll());

    }

}
