package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.common.Result;
import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.dao.UserDao;
import com.example.caigouapp.entity.*;
import com.example.caigouapp.service.MenuService;
import com.example.caigouapp.service.UserService;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://106.53.148.37:8082", maxAge = 3600)
@RestController
public class MenuController {
    @Resource
    private MenuService menuService;
    @Resource
    private UserService userService;

    @Autowired
    MenuDao menudao;
    UserDao userDao;

    /**
     * 接收关键词
     * @param name
     * @return
     */
    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public JSONObject m1(@RequestBody String name){


        JSONObject par = JSONObject.parseObject(name);
        String str = par.getString("searchWord");
        //获得符合条件的菜谱列表
        List<Menu> m1 = menuService.findMenu(str);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(m1));
        JSONObject res = new JSONObject();
        res.put("menus",array);
        res.put("code",200);
        res.put("message","success");
        return res;

    }

    /**
     * 根据ID获取菜谱
     * @param id
     * @return
     */
    @RequestMapping(value = "/menuid",method = RequestMethod.POST)
    public JSONObject m2(@RequestBody String id){


        JSONObject par = JSONObject.parseObject(id);
        Integer str = par.getInteger("searchId");
        List<Menu> m1 = menuService.findByid(str);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(m1));
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        res.put("menus",array);
        return res;

    }


    /**
     * 根据菜谱ID查询菜谱详细信息
     * @param body
     * @return
     */
    @RequestMapping(value = "/menuInfo",method = RequestMethod.POST)
    public JSONObject findMenuInfo(@RequestBody String body){
        JSONObject par = JSONObject.parseObject(body);
        int id = par.getInteger("id");

        Menu menu = menuService.findByMenu(id);
        //创建实体类对象，将从相关表查询到的数据放进对象里
        MenuInfo menuInfo= new MenuInfo();
        menuInfo.setId(menu.getId());
        menuInfo.setName(menu.getName());
        menuInfo.setTags(menu.getTags());
        menuInfo.setMethod(menu.getMethod());
        menuInfo.setAvatar(menu.getAvatar());
        //获取所需材料的list，循环获取信息
        MenuFood mf = menuService.findByMenuId(id);
        String foodList = mf.getFood_id_list();
        String[] A= foodList.split(",");
        //将获取到的
        for (String s : A) {
            int ID = Integer.parseInt(s);
            Food f =menuService.findByFoodId(ID);
            menuInfo.getFood().add(f);
        }
        menuInfo.setFood_weight_list(mf.getFood_weight_list());
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        res.put("data",menuInfo);
        return  res;
    }

    /**
     * 推荐菜谱接口
     * @param body
     * @return
     */
    @RequestMapping(value = "/recommend",method = RequestMethod.POST)
    public JSONObject recommend(@RequestBody String body){
        JSONObject par = JSONObject.parseObject(body);
        int id = par.getInteger("id");

        User user = userService.findById(id);
        String str = user.getTags();
//        System.out.println(str);
        //判断标签是否为空
        if(str == null||str.equals("")){
           Menu menu = menuService.getMenuRan();
            JSONObject res = new JSONObject();
            res.put("message","success");
            res.put("code",200);
            res.put("data",menu);
            return  res;
        }

        String[] A= str.split(",");
        //随机挑选标签
        Integer len = A.length;
        Random random = new Random();
        Integer rand;
        rand = random.nextInt(len);//0-LEN-1的随机数
        int ID = Integer.parseInt(A[rand]);
        String tag = menuService.selectTagById(ID);
        Menu menu = menuService.findRandomMenu(tag);
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        res.put("data",menu);
        return  res;

    }

}
