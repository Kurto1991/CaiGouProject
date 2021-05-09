package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart,Integer> {
}
