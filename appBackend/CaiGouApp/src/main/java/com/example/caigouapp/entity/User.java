package com.example.caigouapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String user_name;
    private String account_num;

    //返回json数据时，忽略密码字段
    @JsonIgnore
    private String password;
    private String phone;
    private String signature;
    private String tags;
    private String avatar;
}
