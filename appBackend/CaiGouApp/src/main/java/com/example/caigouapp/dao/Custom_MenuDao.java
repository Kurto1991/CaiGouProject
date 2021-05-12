package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Custom_Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Custom_MenuDao extends JpaRepository<Custom_Menu,Integer> {

//    /**
//     *根据菜谱ID查询自定义菜谱
//     * @param id
//     * @return
//     */
//    @Query(value ="select cm from Custom_Menu cm where cm.menu_id = :id")
//    Custom_Menu selectCustMenuByMid(@Param("id")Integer id);


    /**
     * 根据购物车的自定义菜谱ID查询自定义菜谱
     * @param custom_menuid
     * @return
     */
    @Query(value ="select cm from  Custom_Menu  cm where cm.id = :custom_menuid")
    Custom_Menu selectCustMenuById(@Param("custom_menuid")Integer custom_menuid);


}