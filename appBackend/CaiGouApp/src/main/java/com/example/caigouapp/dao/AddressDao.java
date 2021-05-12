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
//    @Modifying
//    @Query(value = "")

    /**
     * 根据用户id查询其收获地址
     * @param user_Id
     * @return List<Address>
     */
    @Query(value = "select * from address where user_id= ?1", nativeQuery = true)
    List<Address> findByUser_id(Integer user_Id);

    //
    @Query(value = "select a from Address a where  a.user_id = :user_id")
    Address findAddByUid(@Param("user_id")Integer user_id);
}