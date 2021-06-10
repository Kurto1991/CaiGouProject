package com.example.caigouapp.dao;
import com.example.caigouapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    /**
     * 根据账号查询用户
     * @param account_num)
     * @return
     */
    @Query(value = "SELECT * from user where user.account_num = ?1",nativeQuery = true)
    User findByAccount_num(String account_num);

    /**
     * 查询10个用户
     * @param index
     * @return
     */
    @Query(value = "SELECT * from user LIMIT ?1,10", nativeQuery = true)
    List<User> findAllUser(Integer index);

    @Query(value = "SELECT COUNT(*) from user", nativeQuery = true)
    int countAll();
}
