package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartDaoTest {
    @Autowired
    private CartDao cartDao;

    @Test
    public void insert(@RequestParam("id") Integer id, @RequestParam("user_id")Integer user_id, @RequestParam("menus")Integer menus, @RequestParam("num")Integer num){
       Cart cart = new Cart();
        cart.setId(id);
        cart.setUser_id(user_id);
        cart.setMenus(menus);
        cart.setNum(num);
        cartDao.save(cart);
//        Integer rows = cartDao.insert(1,2,"3",4);
//        System.err.println("rows=" + rows);
//        System.err.println(cart);

    }

}