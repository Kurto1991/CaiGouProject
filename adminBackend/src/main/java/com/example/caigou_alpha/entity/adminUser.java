package com.example.caigou_alpha.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 *用于管理员登陆的验证
 *
 */
@Entity
public class adminUser {//管理员用户表用于登陆
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//标识主键为自动递增
    private int id;
    private String app_admin_name;
    private int app_admin_num;
    private String password;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApp_admin_name() {
        return app_admin_name;
    }

    public void setApp_admin_name(String app_admin_name) {
        this.app_admin_name = app_admin_name;
    }

    public int getApp_admin_num() {
        return app_admin_num;
    }

    public void setApp_admin_num(int app_admin_num) {
        this.app_admin_num = app_admin_num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
