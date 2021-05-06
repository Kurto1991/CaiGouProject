package com.example.caigou_alpha.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "menu_food")
@Entity
public class MenuFood {
    @Id
    private Integer id;
    private Integer menu_id;
    private String food_id_list;
    private String food_weight_list;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public String getFood_id_list() {
        return food_id_list;
    }

    public void setFood_id_list(String food_id_list) {
        this.food_id_list = food_id_list;
    }

    public String getFood_weight_list() {
        return food_weight_list;
    }

    public void setFood_weight_list(String food_weight_list) {
        this.food_weight_list = food_weight_list;
    }
}
