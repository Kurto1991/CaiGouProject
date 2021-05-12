package com.example.caigouapp.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.caigouapp.common.Result;
import com.example.caigouapp.entity.*;
import com.example.caigouapp.service.CartService;
import com.example.caigouapp.service.Custom_MenuService;
import com.example.caigouapp.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CartController {
    @Resource
    private CartService cartService;
    @Resource
    private MenuService menuService;
    @Resource
    private Custom_MenuService custom_menuService;
//    /**
//     * 添加购物车数据到数据库
//     * @param id
//     * @param user_id
//     * @param menus
//     * @return
//     */
//    @RequestMapping(value = "/cart",method = RequestMethod.POST)
//    public  Result insert(@RequestParam("id") Integer id, @RequestParam("user_id")Integer user_id, @RequestParam("menus")String menus){
//
//        Cart cart = new Cart();
//        cart.setId(id);
//        cart.setUser_id(user_id);
//        cart.setMenus(menus);
//        cartService.save(cart);
//        return Result.success(cart);
//
//    }

//    @RequestMapping(value = "/menuinfo",method = RequestMethod.POST)
//    public List<Cart> select(Integer menus, Integer user_id){
//        return cartService.select(menus,user_id);
////        return menuService.menuInfo(menus);
//    }

    @RequestMapping(value = "/updata/custMenu",method = RequestMethod.POST)
    public JSONObject updataCustMenu(@RequestBody String Body){
        JSONObject par = JSONObject.parseObject(Body);
        int menuId = par.getInteger("id");
        /*Cart cart = cartService.findCartById(userId);
        cart.setCustom_menuid(str1);
        cartService.save(cart);*/
        Custom_Menu custom_menu = new Custom_Menu();

        custom_menu.setMenu_id(menuId);

        String menus = menuService.menu_food_info(menuId);

        custom_menu.setFood_id_list(menus);

        String weight = par.getString("Multiple");
        custom_menu.setMultiple_list(weight);

        String foodlist =par.getString("food_id_list");
        custom_menu.setFood_id_list(foodlist);

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
//        CartMenuInfoList cartMenuInfoList = new CartMenuInfoList();
        JSONObject par = JSONObject.parseObject(Body);
        Integer id = par.getInteger("user_id");
        Cart cart = cartService.selectCartByUserId(id);
        String cust_list = cart.getCustom_menuid();
        CartMenuInfoList cartMenuInfoList = cartService.getCartMenuInfo(cust_list);

        JSONObject res = new JSONObject();
        res.put("message","success");
        res.put("code",200);
        res.put("data",cartMenuInfoList);
        return  res;

    }


}
