package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {

    /**
     * 查询某用户在购物车里商品的信息
     * @param menus
     * @param user_id
     * @return
     */
    @Query(value = "select c from Cart c where c.menus=:menus and c.user_id = :user_id")
    List<Cart> select(@Param("menus")Integer menus, @Param("user_id")Integer user_id);
//
////    @Transactional
////    @Modifying
////    @Query(value = "insert into Cart c(c.id,c.user_id,c.menus,c.num) values (:id,:user_id,:menus,:num)",nativeQuery = true)
////    Integer insert(@Param("id")Integer id,@Param("user_id")Integer user_id,@Param("menus")String menus,@Param("num")Integer num);
//
////    Integer save();
//    @Query(value = ("update Cart c set c.num = :num where c.menus=:menus"),nativeQuery = true)
//    Integer update(@Param("num")Integer num,@Param("menus")String menus);


}





