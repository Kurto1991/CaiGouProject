package com.example.caigouapp.service;

import com.example.caigouapp.dao.MenuDao;
import com.example.caigouapp.entity.Menu;
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



}
