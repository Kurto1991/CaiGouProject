package com.example.caigouapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.dao.AddressDao;
import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.util.List;

@SpringBootTest
class CaigouappApplicationTests {

    @Autowired
    MenuDao menuDao;
    @Autowired
    AddressDao addressDao;

    @Test
    void contextLoads() {
        String strings = menuDao.menu_food_info(4);
        strings.split("");

        System.out.println(strings);
    }
    @Test
    void  c(){

        System.out.println(addressDao.selectAddById(2));
    }
    @Test
     void getMenuinfo(){
//          Object[] a=menuDao.findMenuId(4);
//        System.out.println(a[0]);
//       MenuInfo menuInfo=  new ObjectMapper().convertValue(a,MenuInfo.class);
//        MenuInfo menuInfo = menuDao.findMenuId(4);
////        String A =menuInfo.getFood_id_list();
////        System.out.println(A);
//        System.out.println(menuInfo.toString());
//        MenuInfo menuInfo =new MenuInfo();J
//      MenuInfo OB=menuDao.findMenuId(4);
//
////      MenuInfo menuInfo=JSON.parseObject(JSON.toJSONString(menuDao.findMenuId(4)),MenuInfo.class);
//        menuDao.findMenuId(4);
//        MenuInfo
//        System.out.println(OB.toString());
        Menu menu = menuDao.findByMenu(4);
        MenuInfo menuInfo= new MenuInfo();
        menuInfo.setId(menu.getId());
        menuInfo.setName(menu.getName());
        menuInfo.setTags(menu.getTags());
        menuInfo.setMethod(menu.getMethod());
        menuInfo.setAvatar(menu.getAvatar());

        MenuFood mf = menuDao.findByMenuId(4);
        String foodList = mf.getFood_id_list();
        String[] A= foodList.split(",");
        for (String s : A) {
            int id = Integer.parseInt(s);
            Food f =menuDao.findByFoodId(id);
            menuInfo.getFood().add(f);
            System.out.println(f.toString());
        }
        menuInfo.setFood_weight_list(mf.getFood_weight_list());
        System.out.println(menuInfo.toString());



//        System.out.println(foodList);

        //System.out.println(menu.getMethod());



    }

}
