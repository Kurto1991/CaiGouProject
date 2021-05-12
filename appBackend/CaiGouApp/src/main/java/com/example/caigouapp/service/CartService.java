package com.example.caigouapp.service;

import com.example.caigouapp.dao.CartDao;
import com.example.caigouapp.dao.Custom_MenuDao;
import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartService {
    @Resource
    private CartDao cartDao;
    @Resource
    private Custom_MenuDao custom_menuDao;
    @Resource
    private  MenuDao menuDao;

    public void save(Cart c){
        cartDao.save(c);
    }

//    public List<Cart> select(Integer menus, Integer use_id){
//        return cartDao.select(menus,use_id);
//    }

    /**
     * 找用户购物车里的菜谱列表
     * @param id
     * @return
     */
    public String findCartMenu(Integer id){
        return cartDao.findCartMenus(id);

    }
//    public Menu findMenuById(Integer id){
//        return cartDao.findMenuById(id);
//    }




   public Cart findCartById(Integer user_id){
        return cartDao.findCartById(user_id);
   }

   public Cart selectCartByUserId(Integer user_id){
        return cartDao.selectCartByUserId(user_id);
   }

   //获取购物车列表信息
    public CartMenuInfoList getCartMenuInfo(String str){
        //创建购物车商品对象
        CartMenuInfoList cartMenuInfoList = new CartMenuInfoList();
//        cartMenuInfoList.setId(1);




        String[] A= str.split(",");
        //等会会循环
        for (String s : A) {
            CartMenuInfo cartMenuInfo =new CartMenuInfo();
            int id = Integer.parseInt(s);
            Custom_Menu custom_menu = custom_menuDao.selectCustMenuById(id);
            cartMenuInfo.setId(custom_menu.getMenu_id());
            cartMenuInfo.setPrice(custom_menu.getPrice());
            cartMenuInfo.setMultiple(custom_menu.getMultiple_list());
            int a = custom_menu.getMenu_id();
            Menu menu = menuDao.selectMenuById(a);
            cartMenuInfo.setAvatar(menu.getAvatar());
            cartMenuInfo.setMethod(menu.getMethod());
            cartMenuInfo.setName(menu.getName());
            cartMenuInfo.setTags(menu.getTags());
            MenuFood menuFood = menuDao.findByMenuId(cartMenuInfo.getId());
            cartMenuInfo.setFood_weight_list(menuFood.getFood_weight_list());

            String foodList = menuFood.getFood_id_list();
            String[] B= foodList.split(",");
            for (String s1 : B) {
                int ID = Integer.parseInt(s1);
                Food f =menuDao.findByFoodId(ID);
                cartMenuInfo.getFood().add(f);

            }
            cartMenuInfoList.getInfo().add(cartMenuInfo);


        }

        return cartMenuInfoList;
    }



}
