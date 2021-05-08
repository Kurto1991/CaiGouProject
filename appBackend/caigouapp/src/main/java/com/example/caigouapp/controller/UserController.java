package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.annotation.PassToken;
import com.example.caigouapp.common.Constant;
import com.example.caigouapp.common.Result;
import com.example.caigouapp.entity.User;
import com.example.caigouapp.service.UserService;
import com.example.caigouapp.untils.JwtUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PassToken
    @GetMapping("/getById/{id}")
    public Result<User> findById(@PathVariable("id") Integer id) {
        User user = userService.findById(id);
        if(user != null){
            return Result.success(user);
        }
        return Result.error(Constant.NOT_FOUND,"用户不存在");
    }

    /**
     * 用户登录接口
     * @param account
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestParam(name = "account", required = true) String account,
                        @RequestParam(name = "password", required = true) String password){
        JSONObject jsonObject = new JSONObject();

        User userBase = userService.findByAccount(account);

        if(userBase == null){
            jsonObject.put("msg","登录失败，用户不存在");

            return jsonObject;
        }
        else {
            if(!userBase.getPassword().equals(password)){
                jsonObject.put("msg","登录失败，密码错误");

                return jsonObject;
            }
            else {
                String token = JwtUntil.getToken(userBase);

                jsonObject.put("token",token);
                jsonObject.put("user",userBase);

                return jsonObject;
            }
        }
    }

    /**
     * 用户注册接口
     * @param user
     * @return Result
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public Result signUp(@RequestBody  User user){
        userService.saveUser(user);

        return Result.success();
    }

}
