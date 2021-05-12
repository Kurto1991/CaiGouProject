package com.example.caigouapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class UserOrderInfoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String storeName;
    private Integer orderState;
    private Integer orderNumber;
    private String oderCreatTime;
    private String oderServeTime;
    private String Address;
    private String phone;
    private String remark;
    private Double price;
    @OneToMany
    private List<UserOrderInfo> Info = new ArrayList<UserOrderInfo>();


}
