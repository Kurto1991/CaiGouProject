package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.MenuDao;
import com.example.caigou_alpha.entity.Menu;
import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MenuService {
    @Resource
    private MenuDao menuDao;
    public Page<Menu> findPage(Integer pageNum, Integer pageSize){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findAll(pageable);
    }

    public Page<Menu> findLike(Integer pageNum, Integer pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return menuDao.findLikeDao(pageable,name);
    }
}
