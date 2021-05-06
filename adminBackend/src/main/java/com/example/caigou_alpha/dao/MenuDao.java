package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu,Integer> {
    @Query(value = "SELECT m FROM  Menu m  where m.name like %:name%")
    Page<Menu> findLikeDao(@Param("name") String name, Pageable pageable);

}
