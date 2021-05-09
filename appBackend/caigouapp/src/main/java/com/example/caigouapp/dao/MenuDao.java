package com.example.caigouapp.dao;
import com.example.caigouapp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface MenuDao extends JpaRepository<Menu,Integer>{

    List <Menu> findByid(Integer id);
    @Query(value = "select m from Menu m where m.name like %:name% or m.tags like %:name%")
    List<Menu> findMenu (@Param("name") String str);



}
