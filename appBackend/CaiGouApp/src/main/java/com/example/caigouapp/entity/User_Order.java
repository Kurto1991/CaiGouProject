package com.example.caigouapp.entity;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Data
@Entity
public class User_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer user_id ;
    private String remark;
    private Integer status;
    private String custom_menuid_list;
    private Integer store_id;
    private double price;
    private String address;
    private String phone;
    private String createtime;
    private String deliverytime;


}
