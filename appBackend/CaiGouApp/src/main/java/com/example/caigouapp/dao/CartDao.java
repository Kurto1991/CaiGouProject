package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Cart;
import com.example.caigouapp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {


    /**
     *根据用户ID查询购物车表获得自定义菜谱ID
     * @param id
     * @return
     */
    @Query(value = "select c.custom_menuid from Cart c where c.user_id = :id")
    String findCartMenus(@Param("id")Integer id);

    /**
     * 根据用户ID查询购物车表
     * @param user_id
     * @return
     */
    @Query(value = "select  c from Cart c where  c.user_id = :user_id")
    Cart findCartById(@Param("user_id")Integer user_id);

    /**
     * 根据用户ID查询购物车表
     * @param user_id
     * @return
     */
    @Query(value = "select c from Cart c where c.user_id = :user_id")
    Cart selectCartByUserId(@Param("user_id")Integer user_id);

    /**
     * 根据ID删除自定义菜谱
     * @param id
     * @return
     */


//    @Modifying
//    @Transactional
//    @Query(value = "delete  from custom_menu where id = ?1",nativeQuery = true)
//    Void delete(Integer id);




}





