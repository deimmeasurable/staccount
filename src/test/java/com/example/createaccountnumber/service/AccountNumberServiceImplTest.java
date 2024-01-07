package com.example.createaccountnumber.service;

import com.example.createaccountnumber.dtos.AccountNumberRequest;
import com.example.createaccountnumber.dtos.AccountNumberResponse;
import com.example.createaccountnumber.repository.AccountNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountNumberServiceImplTest {
    @Autowired
   private AccountNumberService accountNumberService;
    @Autowired
    private AccountNumberRepository accountNumberRepository;


    @BeforeEach
    void setUp() {
        accountNumberService=new AccountNumberServiceImpl(accountNumberRepository);

    }
    @Test
    void testAccountNumberCanbeCreated(){
        AccountNumberRequest accountNumberRequest= new AccountNumberRequest();
        accountNumberRequest.setBankCode("113");
        accountNumberRequest.setModuleValue(10);

        AccountNumberResponse accountNumberResponse=accountNumberService.generateSTAccountNumber(accountNumberRequest);

        assertEquals(accountNumberRequest.getBankCode(),"113");
        assertEquals(accountNumberRequest.getModuleValue(),10);

       // assertEquals(accountNumberResponse.getAccountNumber(),"");
    }
}