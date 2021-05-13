package com.example.caigouapp.controller;
import com.example.caigouapp.annotation.UserLoginToken;
import com.example.caigouapp.entity.Tag;
import com.example.caigouapp.service.TagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "http://106.53.148.37/:8082", maxAge = 3600)
@RestController
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 查询用户标签
     * @return
     */
    @UserLoginToken
    @RequestMapping(value = "/user/getTag",method = RequestMethod.GET)
    public List<Tag> findAll(){
        return tagService.findAll();
    }
}
