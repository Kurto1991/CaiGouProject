package com.example.caigouapp.service;

import com.example.caigouapp.dao.TagDao;
import com.example.caigouapp.entity.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagService {
    @Resource
    private TagDao tagDao;

    /**
     * 查询标签
     * @return
     */
    public List<Tag> findAll(){
        return tagDao.findAll();
    }

    /**
     * 根据id查找tag
     * @param id
     * @return
     */
    public Tag findTagById(Integer id){
        return tagDao.findById(id).get();
    }

}
