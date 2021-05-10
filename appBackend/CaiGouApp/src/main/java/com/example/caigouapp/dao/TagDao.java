package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagDao extends JpaRepository<Tag,Integer> {
    @Query(value = "select t from Tag t")
    List<Tag> findAll();
}
