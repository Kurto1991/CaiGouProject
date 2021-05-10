package com.example.caigouapp.controller;

import com.example.caigouapp.common.Result;
import com.example.caigouapp.entity.Tag;
import com.example.caigouapp.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TagController {
    @Resource
    private TagService tagService;

    @RequestMapping(value = "/user/getTag",method = RequestMethod.GET)
    public Result findAll(){
      return   Result.success(tagService.findAll());
    }
}
