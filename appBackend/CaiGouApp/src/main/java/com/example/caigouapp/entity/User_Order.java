package com.example.caigouapp.entity;

import lombok.Data;

import javax.persistence.*;

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
    private Integer address =0;

}
