package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.entity.Menu;
import com.example.caigouapp.service.MenuService;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MenuController {
    @Resource
    private MenuService menuService;


    @Autowired
    MenuDao menudao;
    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public JSONObject m1(@RequestBody String name){


        JSONObject par = JSONObject.parseObject(name);
        String str = par.getString("searchWord");
        List<Menu> m1 = menuService.findMenu(str);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(m1));
        JSONObject res = new JSONObject();
        res.put("menus",array);
        res.put("code",200);
        res.put("message","success");
//        JSONArray array=new JSONArray();
        return res;

    }

    @RequestMapping(value = "/menuid",method = RequestMethod.POST)
    public JSONObject m2(@RequestBody String id){


        JSONObject par = JSONObject.parseObject(id);
        Integer str = par.getInteger("searchId");
        List<Menu> m1 = menuService.findByid(str);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(m1));
        JSONObject res = new JSONObject();
        res.put("menus",array);
        res.put("code",200);
        res.put("message","success");

        return res;

    }
}
