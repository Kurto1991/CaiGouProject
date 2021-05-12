package com.example.caigouapp.service;

import com.example.caigouapp.dao.CartDao;
import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.entity.Cart;
import com.example.caigouapp.entity.Food;
import com.example.caigouapp.entity.Menu;
import com.example.caigouapp.entity.MenuFood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService {
    @Resource
    private MenuDao menuDao;
    public List<Menu> findMenu(String str){
        return menuDao.findMenu(str);
    }

    public List<Menu> findByid(Integer id){
        return menuDao.findByid(id);
    }
    public String menuInfo(Integer id){
//        return menuDao.menuInfo(id);
        return null;
    }
    public Menu findByMenu(Integer id){
        return  menuDao.findByMenu(id);
    }


   public MenuFood findByMenuId(Integer id){
        return menuDao.findByMenuId(id);
   }

    public Food findByFoodId(Integer id){
        return menuDao.findByFoodId(id);
    }


    public void save(Menu c){
        menuDao.save(c);
    }

    public String menu_food_info(Integer id){
        return menuDao.menu_food_info(id);
    }

    public Menu selectMenuById(Integer id){
        return menuDao.selectMenuById(id);
    }




}
