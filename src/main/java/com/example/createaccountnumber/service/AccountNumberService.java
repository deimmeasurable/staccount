package com.example.createaccountnumber.service;

import com.example.createaccountnumber.dtos.AccountNumberRequest;
import com.example.createaccountnumber.dtos.AccountNumberResponse;

public interface AccountNumberService {
    AccountNumberResponse generateSTAccountNumber(AccountNumberRequest accountNumberRequest);
}
