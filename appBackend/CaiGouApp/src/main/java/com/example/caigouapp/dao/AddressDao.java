package com.example.caigouapp.dao;
import com.example.caigouapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao extends JpaRepository<Address,Integer> {


    /**
     * 通过用户ID查询地址表
     * @param user_id
     * @return
     */
    @Query(value = "select a from Address a where  a.user_id = :user_id")
    Address findAddByUid(@Param("user_id")Integer user_id);
}