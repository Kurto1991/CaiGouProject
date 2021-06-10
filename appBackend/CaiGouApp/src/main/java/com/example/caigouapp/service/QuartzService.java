package com.example.caigouapp.service;

import com.example.caigouapp.upush.tester.DebugNotification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class QuartzService {
    //每日07：00推送菜谱

    //0 0 7/23 * * ?
    @Scheduled(cron = "0/10 * * * * ?")
    public void hello(){

    }
}
