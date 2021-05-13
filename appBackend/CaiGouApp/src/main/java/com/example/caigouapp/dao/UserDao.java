package com.example.caigouapp.dao;
import com.example.caigouapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    /**
     * 根据账号查询用户
     * @param account_num)
     * @return
     */
    @Query(value = "SELECT * from user where user.account_num = ?1",nativeQuery = true)
    User findByAccount_num(String account_num);
}
