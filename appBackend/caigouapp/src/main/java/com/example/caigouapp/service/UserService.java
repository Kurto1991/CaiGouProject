package com.example.caigouapp.service;

import com.example.caigouapp.dao.UserDao;
import com.example.caigouapp.entity.User;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    @Transactional(rollbackOn = Exception.class)
    public void setUserTags(String tags,Integer account_num){
        userDao.setUserTags(tags,account_num);
    }
    public List<User> findUserByAccount_num(Integer account_num){
        return  userDao.findByAccount_num(account_num);
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;


    /**
     * 通过id查询用户信息
     * @param id
     * @return
     */
    public User findById(Integer id){
        return userDao.findById(id).get();
    }
    /**
     * 通过账号查询用户信息
     * @param account_num
     * @return
     */
    public User findByAccount(String account_num){
        return userDao.findByAccount_num(account_num);
    }

    /**
     * 创建新用户
     * @param user
     */
    public void saveUser(User user) {
        userDao.save(user);
>>>>>>> 6abde5f1f355890dc55c4c2c4a3384fc72b10d87
    }
}
