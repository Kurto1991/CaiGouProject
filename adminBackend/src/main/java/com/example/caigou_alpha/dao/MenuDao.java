package com.example.caigou_alpha.dao;

import com.example.caigou_alpha.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends JpaRepository<Menu,Integer> {
    @Query(value = "SELECT * FROM  menu  where (menu.name like %?2%",nativeQuery = true)
    Page<Menu> findLikeDao(Pageable pageable, String name);
}
