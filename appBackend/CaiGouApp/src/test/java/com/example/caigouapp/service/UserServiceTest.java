package com.example.caigouapp.service;

import com.example.caigouapp.common.Constant;
import com.example.caigouapp.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;

    /**
     * 根据账号查询用户信息
     */
    @Test
    void findUserByAccount() {
        String account = "1";
        User uerBase = new User();


        User user = userService.findUserByAccount(account);

        System.out.println("\n");
        System.out.println(user);
        System.out.println("\n");
    }

    /**
     * 添加用户
     */
    @Rollback(value = true)//回滚事务，防止测试数据进入数据库
    @Test
    void addUser() {
        //初始化一个用户
        User uerBase = new User();
        uerBase.setUser_name("test");
        uerBase.setAccount_num("11356");
        uerBase.setPassword("0678");
        uerBase.setPhone("135905671");
        uerBase.setSignature(null);
        uerBase.setTags(null);
        uerBase.setAvatar(Constant.DEAFULT_AVATAR);

        //添加用户
        userService.addUser(uerBase);
    }

    /**
     * 通过Id查找用户
     */
    @Test
    void findById() {
        Integer id=1;

        User user = userService.findById(id);


        System.out.println("\n\n\n"+user.toString()+"\n\n");
    }

    /**
     * 通过账号查找用户喜爱标签
     */
    @Test
    void findTagsByAccount() {
        String account = "1";

        String[] tags = userService.findTagsByAccount(account);//获取用户标签列表

        System.out.println("\n");
        for (String t:tags){
            System.out.println(t);
        }
        System.out.println("\n");
    }

}