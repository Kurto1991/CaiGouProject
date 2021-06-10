package com.example.caigouapp.upush.tester;

import com.alibaba.fastjson.JSONObject;
import com.umeng.message.util.HttpRequest;

import java.security.MessageDigest;

public class DebugNotification {

    /**
     * 发送透传消息
     */
    public static void send(String userName , String recipeName, String imageUri, String deviceToken) {
        try {
            final AndroidUnicast msg;
            msg = new AndroidUnicast(PushConstants.APP_KEY, PushConstants.APP_MASTER_SECRET);
            msg.setDeviceToken(deviceToken);
            msg.setTicker("test");
            msg.setTitle("今日推荐-"+recipeName);
            msg.setText("Hello "+userName+"!"+"快来pick你的专属推荐菜吧!");
            msg.setPlaySound(true);
            msg.goAppAfterOpen();
            msg.setImg(imageUri);
            msg.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
            msg.setProductionMode();
            Runnable runnable = () -> {
                try {
                    sendImpl(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            new Thread(runnable).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void sendImpl(UmengNotification msg) throws Exception {
        String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
        msg.setPredefinedKeyValue("timestamp", timestamp);

        String url = "http://msg.umeng.com/api/send";
        String postBody = msg.getPostBody();

        String p_sign = "POST" + url + postBody + msg.getAppMasterSecret();
        String sign = md5(p_sign);
        url = url + "?sign=" + sign;
        String response = HttpRequest.post(url).acceptJson().send(postBody).body("UTF-8");
        //JSONObject responseJson = new JSONObject(response);
        //tring ret = responseJson.getString("ret");
        System.out.println(response);
        if (!response.contains("SUCCESS")) {
            System.out.println("发送失败");
        } else {
            System.out.println("发送成功");
        }
    }

    private static String md5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);

        for (byte b : hash) {
            int i = (b & 0xFF);
            if (i < 0x10) hex.append('0');
            hex.append(Integer.toHexString(i));
        }

        return hex.toString();
    }
}
