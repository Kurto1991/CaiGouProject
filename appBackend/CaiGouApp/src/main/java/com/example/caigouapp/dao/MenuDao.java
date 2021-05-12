package com.example.caigouapp.dao;
import com.example.caigouapp.entity.Food;
import com.example.caigouapp.entity.Menu;
import com.example.caigouapp.entity.MenuFood;
import com.example.caigouapp.entity.MenuInfo;
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

//    @Query("select m.name,m.method,m.avatar from  Menu m where m.id = :id")
//    List<String> menuInfo(@Param("id")Integer id);

    /**
     * 查找该菜谱的食材列表
     * @param id
     * @return
     */
    @Query("select f.food_id_list from MenuFood f where f.menu_id = :id")
    String menu_food_info(@Param("id")Integer id);

//mf.food_id_list,
//    @Query(value = "select m.id,m.name,m.tags,m.method,m.avatar,mf.food_id_list,mf.food_weight_list from Menu m , MenuFood mf "
//            +"where m.id = mf.menu_id and m.id=:id")
//     MenuInfo  findMenuId(@Param("id")Integer id);
    @Query(value = "select m from Menu m where m.id = :id")
    Menu findByMenu(@Param("id")Integer id);

    @Query(value = "select mf from MenuFood mf where mf.menu_id= :id")
    MenuFood findByMenuId(@Param("id")Integer id);

    @Query(value = "select f from Food f where f.id = :id")
    Food findByFoodId(@Param("id")Integer id);

//    @Query(value = "select m.id,mf.from Menu m,MenuFood mf")

    @Query(value = "select m from Menu  m where  m.id = :id")
    Menu selectMenuById(@Param("id")Integer id);


}
