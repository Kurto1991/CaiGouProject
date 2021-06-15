package com.example.caigouapp.upush.tester;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebugNotificationTest {

    @Test
    void send() {
        DebugNotification.send("鸽子窝","清蒸鲈鱼","https://st-cn.meishij.net/r/208/102/1025708/s1025708_156345334153163.jpg","AqKZYHGcovVPEH8cdZw30AZ7oCNfUvQwQqYbJrMPlBFG");
    }
}