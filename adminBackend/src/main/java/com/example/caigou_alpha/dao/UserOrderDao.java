package com.example.caigou_alpha.dao;


import com.example.caigou_alpha.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderDao extends JpaRepository<UserOrder,Integer> {
//    @Query(value = "select * from order", nativeQuery = true)
//    Page<Order> findAllpage(Pageable pageable);
}
