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
import java.util.Random;

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

    public Menu findByMenu(Integer id){

        return  menuDao.findByMenu(id);
    }


    public MenuFood findByMenuId(Integer id)
    {
        return menuDao.findByMenuId(id);
    }

    public Food findByFoodId(Integer id){

        return menuDao.findByFoodId(id);
    }


    public String menu_food_info(Integer id){

        return menuDao.menu_food_info(id);
    }

    public Menu selectMenuById(Integer id){

        return menuDao.selectMenuById(id);
    }

    /**
     * 根据ID查找标签
     * @param id
     * @return
     */
    public String selectTagById(Integer id){
        return menuDao.selectTagById(id);
    }

    public Menu selectMenuByTag(String tag){
        return menuDao.selectMenuByTag(tag);
    }

    /**
     * 根据标签获取菜谱数量，然后生成随机数，随机推荐一个菜品
     * @param tag
     * @return
     */
    public Menu findRandomMenu(String tag){
        Integer range;
        range = menuDao.getMenuNum(tag);
        Random random = new Random();
        Integer rand;
        rand = random.nextInt(range);
        return menuDao.getMenuRandom(tag,rand);
    }

//    /**
//     * 获取所有上架菜谱的数量
//     * @return
//     */
//    public Integer getAllMenu(){
//        return menuDao.getAllMenu();
//    }

    public Menu getMenuRan(){
        Integer ran;
        ran = menuDao.getAllMenu();
        Random random = new Random();
        Integer rand;
        rand = random.nextInt(ran);
        return menuDao.getMenuRan(rand);
    }
}
