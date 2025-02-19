package com.example.caigouapp.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.entity.*;
import com.example.caigouapp.service.CartService;
import com.example.caigouapp.service.Custom_MenuService;
import com.example.caigouapp.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin(origins = "http://106.53.148.37:8082", maxAge = 3600)
@RestController
public class CartController {
    @Resource
    private CartService cartService;
    @Resource
    private MenuService menuService;
    @Resource
    private Custom_MenuService custom_menuService;

    @RequestMapping(value = "/updata/custMenu",method = RequestMethod.POST)
    public JSONObject updataCustMenu(@RequestBody String Body){
        JSONObject par = JSONObject.parseObject(Body);
        int menuId = par.getInteger("id");

        Custom_Menu custom_menu = new Custom_Menu();

        custom_menu.setMenu_id(menuId);

        String menus = menuService.menu_food_info(menuId);

        custom_menu.setFood_id_list(menus);

        String weight = par.getString("Multiple");
        custom_menu.setMultiple_list(weight);

        String foodlist =par.getString("food_id_list");
        custom_menu.setFood_id_list(foodlist);

        //在自定义菜谱加一个标准重量字段
//        String weightList = par.getString("weightList");
//        custom_menu.setwight_list(weightList);

        Double price = par.getDouble("price");
        custom_menu.setPrice(price);
        Custom_Menu t = custom_menuService.save(custom_menu);
        //插入custommenu获取id 输入到购物车
        int userId = par.getInteger("userId");
        String str = cartService.findCartMenu(userId);
        String str1 = "";
        if (str == null) str = "";
        str1 = str+t.getId()+",";

        Cart cart = cartService.findCartById(userId);
        cart.setCustom_menuid(str1);
        cartService.save(cart);

        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        return  res;



    }

    //购物车列表
    @RequestMapping(value = "/cart/list",method =RequestMethod.POST)
    public JSONObject cartInfo(@RequestBody String Body){

        JSONObject par = JSONObject.parseObject(Body);
        //提取用户ID
        Integer id = par.getInteger("user_id");
        //查找用户的购物车
        Cart cart = cartService.selectCartByUserId(id);
        //获取购物车的自定义菜谱列表
        String cust_list = cart.getCustom_menuid();
        if(cust_list.equals("")) {

            JSONObject res = new JSONObject();
            res.put("message","购物车为空");
            return res;
        }
        else {


            //创建购物车菜品列表对象接收数据
            CartMenuInfoList cartMenuInfoList = cartService.getCartMenuInfo(cust_list);

            JSONObject res = new JSONObject();
            res.put("message", "success");
            res.put("code", 200);
            res.put("data", cartMenuInfoList);
            return res;

        }

    }

    @RequestMapping(value = "/delete/cart",method = RequestMethod.POST)
    public JSONObject delete(@RequestBody String Body){
        JSONObject par = JSONObject.parseObject(Body);
        int userId = par.getInteger("userId");
        String list = par.getString("customlist");
        String[] A = list.split(",");
        Cart cart = cartService.selectCartByUserId(userId);
        String str = cart.getCustom_menuid();
        //字符串替换并删除对应记录
        for (String s : A) {
            str =str.replace(s+",","");
            int a = Integer.parseInt(s);
            custom_menuService.del(a);
        }
        cart.setCustom_menuid(str);
        cartService.save(cart);
        JSONObject res = new JSONObject();
        res.put("message", "success");
        res.put("code", 200);
        return res;
    }

}
