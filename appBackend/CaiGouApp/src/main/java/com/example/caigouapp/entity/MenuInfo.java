package com.example.caigouapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MenuInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String tags;
    private String method;
    private String avatar;

    @OneToMany
    private List<Food> food = new ArrayList<Food>();
    private String food_weight_list;


}
