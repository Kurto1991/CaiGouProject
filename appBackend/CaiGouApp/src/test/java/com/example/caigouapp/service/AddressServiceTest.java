package com.example.caigouapp.service;

import com.example.caigouapp.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AddressServiceTest {

    @Autowired
    AddressService addressService;

    /**
     * 根据id获取用户地址，其返回值List<Address>
     */
    @Test
    void findAddressByUserId() {
        Integer userId = 1;//用户Id

        //获取用户的所有收货地址
        List<Address> addressList = addressService.findAddressByUserId(userId);

        System.out.println("\n");
        for (Address address: addressList){
            System.out.println(address.toString());
        }

        System.out.println("\n");
    }

    /**
     * 根据用户id获取地址，返回一个Address对象
     */
    @Test
    void findAddressById() {
        Integer userId = 1;//用户Id

        Address address = addressService.findAddressById(userId);


        System.out.println("\n");
        System.out.println(address);
        System.out.println("\n");
    }

    /**
     * 更新用户地址
     */
    @Rollback(value = true)
    @Test
    void updateAddress() {
        Integer userId = 1;//用户Id
        Address address = addressService.findAddressById(userId);//获取一个地址

        address.setAddress("test");//修改地址
        address.setPhone("18054759745");//修改电话

        addressService.updateAddress(address);
    }
}