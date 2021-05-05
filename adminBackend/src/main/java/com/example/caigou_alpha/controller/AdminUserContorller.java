package com.example.caigou_alpha.controller;


import com.example.caigou_alpha.entity.AdminUser;
import com.example.caigou_alpha.service.AdminUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@SuppressWarnings("rawtypes")
@CrossOrigin(origins = "http://domain2.com",
        maxAge = 3600,
        methods = {RequestMethod.GET, RequestMethod.POST})
@RestController//标识此接口中所有都是返回json数据
@RequestMapping("/adminUser")//给访问链接加个前缀
public class AdminUserContorller {
    @Resource
    private AdminUserService adminUserService;

    /**
     * 查询是否存在这个账号的用户
     * @param id
     * @return List<User>
     * 若存在返回该用户，不存在返回NULL
     */
    @GetMapping("/getById/{id}")
    public AdminUser findById(@PathVariable Integer id){
        return adminUserService.findById(id);
    }
}
