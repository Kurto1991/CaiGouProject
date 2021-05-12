package com.example.caigouapp.service;

import com.example.caigouapp.dao.UserDao;
import com.example.caigouapp.entity.Cart;
import com.example.caigouapp.entity.User;
import org.springframework.data.repository.query.Param;
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
    }



}
