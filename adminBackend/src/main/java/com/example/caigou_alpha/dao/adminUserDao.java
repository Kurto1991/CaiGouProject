package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.adminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface adminUserDao extends JpaRepository<adminUser,Integer> {
    @Query(value = "select * from app_admin where account = ?1",nativeQuery = true)
    List<adminUser> findAccount(String account);
}
