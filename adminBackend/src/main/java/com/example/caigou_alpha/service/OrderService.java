package com.example.caigou_alpha.service;

import com.example.caigou_alpha.dao.UserOrderDao;
import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource//获得将其他部分资源引入
    private UserOrderDao userOrderDao;

    public Page<UserOrder> findPage(Integer pageNum, Integer pageSize){
        //Sort sort =  Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return userOrderDao.findAll(pageable);
    }
}
