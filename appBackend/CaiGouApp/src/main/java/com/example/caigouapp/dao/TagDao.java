package com.example.caigouapp.dao;
import com.example.caigouapp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag,Integer> {
}
