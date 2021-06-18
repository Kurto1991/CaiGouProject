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


    public void save(User_Order c){

        user_orderDao.save(c);
    }


    /**
     * 根据用户ID查询用户订单的详细信息
     * userOrderInfoListAll--userOrderInfoList--userOrderInfo
     * 嵌套
     * @param user_id
     * @return
     */
    public UserOrderInfoListAll getUserOrderInfo(Integer user_id){

        List<User_Order> user_order = user_orderDao.selectUserOrderByUserId(user_id);
        //创建拥有该用户所有订单信息的对象
        UserOrderInfoListAll userOrderInfoListAll = new UserOrderInfoListAll();
        //获取一个订单的信息
        for (User_Order userOrder : user_order) {
            UserOrderInfoList userOrderInfoList = new UserOrderInfoList();
            userOrderInfoList.setRemark(userOrder.getRemark());//备注
            userOrderInfoList.setOrderNumber(userOrder.getId());//订单号
            userOrderInfoList.setStoreName("顺丰超市");
            userOrderInfoList.setOrderState(userOrder.getStatus());
            userOrderInfoList.setOderCreatTime(userOrder.getCreatetime());
            userOrderInfoList.setOderServeTime(userOrder.getDeliverytime());
//            userOrderInfoList.setOderServeTime("2021-05-15  15:55:77");
            userOrderInfoList.setPrice(userOrder.getPrice());
//            Address address = addressDao.findAddByUid(user_id);
            userOrderInfoList.setPhone(userOrder.getPhone());
            userOrderInfoList.setAddress(userOrder.getAddress());
            //获取一个订单中的一个菜谱的信息
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
                //获取一个菜谱的食材信息
                String str1 = custom_menu.getFood_id_list();
                String[] B = str1.split(",");
                for (String s1 : B) {
                    int ID = Integer.parseInt(s1);
                    Food f =menuDao.findByFoodId(ID);
                    userOrderInfo.getList().add(f);
                }
                //将一个菜谱加入到一个订单中
                userOrderInfoList.getInfo().add(userOrderInfo);
            }
            //将一个订单加入订单列表
            userOrderInfoListAll.getInfo().add(userOrderInfoList);
        }
        //返回订单列表
        return userOrderInfoListAll;
    }


    public User_Order selectOrder(int id){
        return user_orderDao.selectOrder(id);
    }

}



