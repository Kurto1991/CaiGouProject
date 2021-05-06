package com.example.demo.dao;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    public User findByTags(String tags);
}
