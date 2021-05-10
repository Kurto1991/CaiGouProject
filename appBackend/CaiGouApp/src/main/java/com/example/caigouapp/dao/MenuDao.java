package com.example.caigouapp.dao;
import com.example.caigouapp.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuDao extends JpaRepository<Menu,Integer>{

    List <Menu> findByid(Integer id);
    @Query(value = "select m from Menu m where m.name like %:name% or m.tags like %:name%")
    List<Menu> findMenu (@Param("name") String str);

    @Query("select m.name,m.method,m.avatar from  Menu m where m.id = :id")
    List<String> menuInfo(@Param("id")Integer id);

    /**
     * 查找该菜谱的食材列表
     * @param id
     * @return
     */
    @Query("select f.food_id_list from MenuFood f where f.menu_id = :id")
    String menu_food_info(@Param("id")Integer id);


    @Query(value = "select m.id,m.name,m.tags,m.method,m.avatar,mf.food_id_list,mf.foo_weight_list from Menu m left join MenuFood mf on m.id = mf.menu_id")
    public List<Menu> findAll();



}
