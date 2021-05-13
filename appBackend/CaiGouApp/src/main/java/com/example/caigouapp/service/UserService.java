package com.example.caigouapp.service;

import com.example.caigouapp.dao.UserDao;
import com.example.caigouapp.entity.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserService {
    @Autowired
    UserDao userDao;


    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    public User findUserByAccount(String account){
        return userDao.findByAccount_num(account);
    }

    /**
     * 新增一个用户
     * @param user
     */
    @Transactional
    public void addUser(User user){
        userDao.save(user);
    }

    /**
     * 根据ID查询用户
     * @param userId
     * @return
     */
    public User findById(Integer userId) {
        return userDao.findById(userId).get();
    }

    /**
     * 根据用户账号查询其喜爱标签
     * @param account
     * @return String[]
     */
    public String[] findTagsByAccount(String account) {
        User user = userDao.findByAccount_num(account);

        String tags = user.getTags();
        //用户tags为空
        if(StringUtils.isBlank(tags)){
            String[] error ={"empty"};
            return  error;
        }
        else{
            String[] tagsSplit = tags.split(",");

            return tagsSplit;
        }

    }
    /**
     *有ID则为修改
     * @param user
     */
    public void update(User user){
        userDao.save(user);
    }
}

