package com.example.caigouapp.controller;

import com.example.caigouapp.common.Constant;
import com.example.caigouapp.common.Result;
import com.example.caigouapp.entity.User;
import com.example.caigouapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")//访问链接前缀
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查找对应ID用户信息
     * @param id
     * @return 若存在则返回该用户，否则返回错误信息
     */
    @GetMapping("/getById/{id}")
    public Result<User> findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        if(user != null){
            return Result.success(user);
        }
        return Result.error(Constant.NOT_FOUND,"用户不存在");
    }
}
