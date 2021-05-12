package com.example.caigouapp.service;

import com.example.caigouapp.dao.Custom_MenuDao;
import com.example.caigouapp.entity.Cart;
import com.example.caigouapp.entity.Custom_Menu;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class Custom_MenuService {
    @Resource
    private Custom_MenuDao custom_menuDao;


//    public Custom_Menu selectCustMenuByMid(Integer id){
//        return custom_menuDao.selectCustMenuByMid(id);
//    }

    public Custom_Menu save(Custom_Menu c){
        return custom_menuDao.save(c);
    }

    public  Custom_Menu selectCustMenuById(Integer custom_menuid){
        return custom_menuDao.selectCustMenuById(custom_menuid);
    }

}
