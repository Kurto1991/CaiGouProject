package com.example.caigouapp.dao;

import com.example.caigouapp.entity.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressDaoTest {

    @Autowired
    AddressDao addressDao;
    @Test
    void findAddressById() {

        Address address = addressDao.findAddressById(2);

        System.out.println("\n"+address.toString());
    }

    @Test
    void addNum(){

        System.out.println(addressDao.getUserAddressNum(1));
    }
}