package com.example.caigouapp.entity;

import lombok.Data;

/**
 *与前端数据交互的类，主要用于登录与注册时自动关联映射Json数据
 */
@Data
public class UserDTO {

    private  String phone;
    private String password;
}
