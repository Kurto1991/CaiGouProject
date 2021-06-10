package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.annotation.UserLoginToken;
import com.example.caigouapp.entity.*;
import com.example.caigouapp.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


@CrossOrigin(origins = "http://106.53.148.37:8082", maxAge = 3600)
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
    @Resource
    private AddressService addressService;

    //生成订单
//    @UserLoginToken
    @RequestMapping(value = "/order",method = RequestMethod.POST)
    public JSONObject creatOrder(@RequestBody JSONObject Body){
//        JSONObject par = JSONObject.parseObject(Body);
        //得到用户ID
        int user_id = Body.getInteger("user_id");

        //根据用户ID获取默认地址
        Address address = addressService.getAdd(user_id);
        if(address == null)
        {
            JSONObject res = new JSONObject();
            res.put("message","没有设置地址");
            res.put("code",100);
            return  res;
        }
        //通过购物车实体类对象获取信息
        Cart cart = cartService.findCartById(user_id);

        String str = cart.getCustom_menuid();
        System.out.println(str);
        cart.setCustom_menuid("");
        cartService.save(cart);

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
        user_order.setAddress(address.getAddress());
        user_order.setPhone(address.getPhone());
//        user_order.setCreatetime("");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        user_order.setCreatetime(df.format(new Date()));
        user_orderService.save(user_order);
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        return  res;
    }


    //获取订单的详细信息
//    @UserLoginToken
    @RequestMapping(value = "/order/info",method = RequestMethod.POST)

    public JSONObject getOrderInfo(@RequestBody JSONObject Body){
//        JSONObject par = JSONObject.parseObject(Body);
        Integer id = Body.getInteger("user_id");

        UserOrderInfoListAll userOrderInfoListAll = user_orderService.getUserOrderInfo(id);
        System.out.println(44);
        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        res.put("data",userOrderInfoListAll);
        return  res;
    }
}
