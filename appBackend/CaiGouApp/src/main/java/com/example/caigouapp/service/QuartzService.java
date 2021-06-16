package com.example.caigouapp.service;

import com.example.caigouapp.dao.UserDao;
import com.example.caigouapp.entity.Menu;
import com.example.caigouapp.entity.User;
import com.example.caigouapp.upush.tester.DebugNotification;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 每日定时任务，菜谱推送
 */
@Component
public class QuartzService {
    //每日07：00推送菜谱
    @Autowired
    UserDao userDao;

    @Autowired
    MenuService menuService;



    //0 0 7/23 * * ?   0/10 * * * * ?
    @Scheduled(cron = "0 0 7/23 * * ?")
    public void quartzMenu(){
        int index = 0;//数据库数据下标，从第0条数据开始，每10个用户进行一次推送
        int num = userDao.countAll();//用户总数量

        //遍历用户list集合
        while(index <= num){
            List<User> userList = userDao.findAllUser(index);
            for (User user:userList){
                pushToUser(user);
            }

            index+=10;
        }


    }

    /**
     * 异步方法，推送消息
     * @param user
     */
    @Async("qutarzExecutor")
    public void pushToUser(User user){

        //获取用户感兴趣的菜谱tag的id
        String tags = user.getTags();

        //若用户标签为空
        if(tags == null){
            //随机推荐菜谱
            Menu menu = menuService.getMenuRan();

            DebugNotification.send(user.getUser_name(),menu.getName(),menu.getAvatar(),user.getDevicetoken());
        }
        else {

            String[] userTag = tags.split(",");


            int id = Integer.parseInt(userTag[0]);

            String menuTag = menuService.selectTagById(id);

            //根据该tag推荐一道菜
            Menu menu = menuService.findRandomMenu(menuTag);

            //推送消息
            DebugNotification.send(user.getUser_name(),menu.getName(),menu.getAvatar(),user.getDevicetoken());
        }

    }
}
