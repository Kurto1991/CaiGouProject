package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Cart;
import com.example.caigouapp.entity.User_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface User_OrderDao extends JpaRepository<User_Order,Integer> {


    @Query(value = "select c from Cart c where c.user_id=:user_id")
    Cart selectCartByUserId(@Param("user_id")Integer id);

    @Query(value = "select o from User_Order o where o.user_id= :user_id")
    List<User_Order> selectUserOrderByUserId(@Param("user_id")Integer user_id);




}
