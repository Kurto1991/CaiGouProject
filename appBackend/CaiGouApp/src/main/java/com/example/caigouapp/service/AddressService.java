package com.example.caigouapp.service;

import com.example.caigouapp.dao.AddressDao;
import com.example.caigouapp.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressDao addressDao;

    /**
     * 根据用户ID查询其所有收获地址
     * @param userId
     * @return
     */
    public List<Address> findAddressByUserId(Integer userId){
        return addressDao.findByUser_id(userId);
    }

    /**
     * 根据id查询收获地址
     * @param id
     * @return
     */
    public Address findAddressById(Integer id){
        return addressDao.findById(id).get();
    }

    /**
     * 更新用户收获地址
     * @param address
     */
    @Transactional
    public void updateAddress(Address address){
        addressDao.saveAndFlush(address);
    }
}
