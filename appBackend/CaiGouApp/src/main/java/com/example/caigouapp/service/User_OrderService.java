package com.example.caigouapp.service;

import com.example.caigouapp.dao.*;
import com.example.caigouapp.entity.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User_OrderService {
    @Resource
    private User_OrderDao user_orderDao;
    @Resource
    private AddressDao addressDao;
    @Resource
    private Custom_MenuDao custom_menuDao;
    @Resource
    private MenuDao menuDao;

    private Cart selectCartByUserId(Integer id){
        return user_orderDao.selectCartByUserId(id);
    }


    public void save(User_Order c){
        user_orderDao.save(c);
    }


    public UserOrderInfoListAll getUserOrderInfo(Integer user_id){

        List<User_Order> user_order = user_orderDao.selectUserOrderByUserId(user_id);
        //
//        UserOrderInfoList userOrderInfoList = new UserOrderInfoList();
        UserOrderInfoListAll userOrderInfoListAll = new UserOrderInfoListAll();
        for (User_Order userOrder : user_order) {
            UserOrderInfoList userOrderInfoList = new UserOrderInfoList();
            userOrderInfoList.setRemark(userOrder.getRemark());//备注
            userOrderInfoList.setOrderNumber(123456);//订单号
            userOrderInfoList.setStoreName("顺丰超市");
            userOrderInfoList.setOrderState(userOrder.getStatus());
            userOrderInfoList.setOderCreatTime("2021-05-15  15:05:77");
            userOrderInfoList.setOderServeTime("2021-05-15  15:55:77");
            userOrderInfoList.setPrice(userOrder.getPrice());
            Address address = addressDao.findAddByUid(user_id);
            userOrderInfoList.setPhone(address.getPhone());
            userOrderInfoList.setAddress(address.getAddress());

            String str = userOrder.getCustom_menuid_list();
            String[] A = str.split(",");
            for (String s : A) {
                int id = Integer.parseInt(s);
                Custom_Menu custom_menu = custom_menuDao.selectCustMenuById(id);
                UserOrderInfo userOrderInfo =new UserOrderInfo();

                userOrderInfo.setPrice(custom_menu.getPrice());//price
                userOrderInfo.setMenuId(custom_menu.getMenu_id());
                userOrderInfo.setId(custom_menu.getId());
                Menu menu = menuDao.selectMenuById(custom_menu.getMenu_id());
                userOrderInfo.setAvatar(menu.getAvatar());
                userOrderInfo.setMethod(menu.getMethod());
                userOrderInfo.setName(menu.getName());
                userOrderInfo.setTags(menu.getTags());
                userOrderInfo.setMultiple(custom_menu.getMultiple_list());

                String str1 = custom_menu.getFood_id_list();
                String[] B = str1.split(",");
                for (String s1 : B) {
                    int ID = Integer.parseInt(s1);
                    Food f =menuDao.findByFoodId(ID);
                    userOrderInfo.getList().add(f);
                }
                userOrderInfoList.getInfo().add(userOrderInfo);
            }
            userOrderInfoListAll.getInfo().add(userOrderInfoList);
        }
        return userOrderInfoListAll;
    }

}

//        userOrderInfoList.setRemark(user_order.getRemark());
//        userOrderInfoList.setOrderNumber(123456);
//        userOrderInfoList.setOrderState(user_order.getStatus());
//        userOrderInfoList.setOderCreatTime("2021-05-15  15:05:77");
//        userOrderInfoList.setOderServeTime("2021-05-15  15:55:77");
//        userOrderInfoList.setPrice(user_order.getPrice());
//        Address address = addressDao.findAddByUid(user_id);
//        userOrderInfoList.setPhone(address.getPhone());
//        userOrderInfoList.setAddress(address.getAddress());

//        String str = user_order.getCustom_menuid_list();
//        String[] A = str.split(",");
//        for (String s : A) {
//            int id = Integer.parseInt(s);
//            Custom_Menu custom_menu = custom_menuDao.selectCustMenuById(id);
//            UserOrderInfo userOrderInfo =new UserOrderInfo();
//
//            userOrderInfo.setPrice(custom_menu.getPrice());
//            userOrderInfo.setMenuId(custom_menu.getMenu_id());
//            userOrderInfo.setId(custom_menu.getId());
//            Menu menu = menuDao.selectMenuById(custom_menu.getMenu_id());
//            userOrderInfo.setAvatar(menu.getAvatar());
//            userOrderInfo.setMethod(menu.getMethod());
//            userOrderInfo.setName(menu.getName());
//            userOrderInfo.setTags(menu.getTags());
//            userOrderInfo.setMultiple(custom_menu.getMultiple_list());
//
//            String str1 = custom_menu.getFood_id_list();
//            String[] B = str.split(",");
//            for (String s1 : B) {
//                int ID = Integer.parseInt(s1);
//                Food f =menuDao.findByFoodId(ID);
//                userOrderInfo.getList().add(f);
//
//            }
//            userOrderInfoList.getInfo().add(userOrderInfo);
//        }
//        return userOrderInfoList;

