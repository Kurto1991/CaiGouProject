package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.entity.*;
import com.example.caigouapp.service.CartService;
import com.example.caigouapp.service.Custom_MenuService;
import com.example.caigouapp.service.UserService;
import com.example.caigouapp.service.User_OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class User_OrderController {
    @Resource
    private UserService userService;
    @Resource
    private CartService cartService;
    @Resource
    private User_OrderService user_orderService;
    @Resource
    private Custom_MenuService custom_menuService;

    //生成订单
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public JSONObject creatOrder(@RequestBody String Body){
        JSONObject par = JSONObject.parseObject(Body);
        //得到用户ID
        int user_id = par.getInteger("user_id");
        //通过购物车实体类对象获取信息
        Cart cart = cartService.findCartById(user_id);
        String str = cart.getCustom_menuid();
        //分割字符串
        String[] A= str.split(",");

        User_Order user_order = new User_Order();

        //设置订单自定义菜谱列表
        user_order.setCustom_menuid_list(str);
        //订单user_id
        user_order.setUser_id(user_id);

        Double price= 0.00;

        //获得订单的总价
        for (String s : A) {
            int ID = Integer.parseInt(s);
            Custom_Menu custom_menu = custom_menuService.selectCustMenuById(ID);
            price += custom_menu.getPrice();
        }
        //价格
        user_order.setPrice(price);
        user_order.setRemark("。。。");
        user_order.setStatus(4);
        user_order.setStore_id(1);
        user_orderService.save(user_order);
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        return  res;
    }

    //获取订单的详细信息
    @RequestMapping(value = "/order/info",method = RequestMethod.POST)
    public JSONObject getOrderInfo(@RequestBody String Body){
        JSONObject par = JSONObject.parseObject(Body);
        Integer id = par.getInteger("user_id");
        UserOrderInfoListAll userOrderInfoListAll = user_orderService.getUserOrderInfo(id);
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        res.put("data",userOrderInfoListAll);
        return  res;
    }
}
