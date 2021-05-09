package com.example.caigouapp.controller;

import com.example.caigouapp.common.Result;
import com.example.caigouapp.entity.User;
import com.example.caigouapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/getUserTags", method = RequestMethod.POST)
    public Result setUserTags(String tags, Integer account_id) {
        userService.setUserTags(tags, account_id);
        return Result.success();

    }
    /**
     * 返回成功码以及用户相关标签
     */

//    @RequestMapping(value = "/user/getTag", method = RequestMethod.GET)

}