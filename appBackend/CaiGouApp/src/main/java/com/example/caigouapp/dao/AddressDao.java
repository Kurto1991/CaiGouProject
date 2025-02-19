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

    @Query(value = "select a from Address a where  a.user_id = :user_id and a.status = 1")
    Address selectAddById(@Param("user_id") Integer user_id);
    /**
     * 根据用户id查询其收获地址
     * @param user_Id
     * @return List<Address>
     */
    @Query(value = "select * from address where user_id= ?1", nativeQuery = true)
    List<Address> findByUser_id(Integer user_Id);

    /**
     * 根据主键id查找地址
     * @param id
     * @return
     */
    @Query(value = "select * from address where id = ?1", nativeQuery = true)
    Address findAddressById(Integer id);

    /**
     * 根据用户id查找地址数量
     * @param userId
     * @return
     */
    @Query(value = "select count(*) from address where user_id = ?1", nativeQuery = true)
    int getUserAddressNum(int userId);
}