package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Cart;
import com.example.caigouapp.entity.User_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface User_OrderDao extends JpaRepository<User_Order,Integer> {

    /**
     * 根据用户ID查询购物车
     * @param id
     * @return
     */
    @Query(value = "select c from Cart c where c.user_id=:user_id")
    Cart selectCartByUserId(@Param("user_id")Integer id);

    /**
     * 根据用户ID查询用户订单
     * @param user_id
     * @return
     */
    @Query(value = "select o from User_Order o where o.user_id= :user_id  order by o.id DESC")
    List<User_Order> selectUserOrderByUserId(@Param("user_id")Integer user_id);

    @Query(value = "select o from User_Order  o where o.id=:id")
    User_Order selectOrder(@Param("id")Integer id);



}
