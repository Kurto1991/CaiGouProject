package com.example.caigouapp.dao;

import com.example.caigouapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Modifying
    @Query(value = "update User user set user.tags=:tags where user.account_num = :account_num")
    int setUserTags(@Param("tags")String tags,@Param("account_num")Integer account_num);
//    List<User> findUserByAccount_num();

    /**
     * 查询 用户相关标签
     * @param account_num
     * @return
     */
    @Query(value = "select t.tags from User t where t.account_num = :account_num")
    List<User> findByAccount_num(@Param("account_num")Integer account_num);
}
