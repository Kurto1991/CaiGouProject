package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM  user , user_order WHERE user_order.user_id = ?1 AND user_order.user_id = user.id",nativeQuery = true)
    User findOrder(int userId);
}
