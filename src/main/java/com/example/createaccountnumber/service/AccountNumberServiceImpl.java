package com.example.createaccountnumber.service;

import com.example.createaccountnumber.dtos.AccountNumberRequest;
import com.example.createaccountnumber.dtos.AccountNumberResponse;
import com.example.createaccountnumber.model.AccountNumber;
import com.example.createaccountnumber.repository.AccountNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AccountNumberServiceImpl implements AccountNumberService {
    @Autowired
    private final AccountNumberRepository accountNumberRepository;



    @Override
    public AccountNumberResponse generateSTAccountNumber(AccountNumberRequest accountNumberRequest) {
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setId(accountNumber.getId());
        accountNumber.setBankCode(accountNumberRequest.getBankCode());
        accountNumber.setModuleNumber(accountNumberRequest.getModuleValue());

        String randomDigits = generateRandomNumber();
        int total = multiplyDigits(accountNumber.getBankCode(), randomDigits);
        String checkDigit = String.valueOf(subtractReminderValueFromModule(accountNumberRequest.getModuleValue(), total));

        String generatedAccountNumber = randomDigits + checkDigit;
        accountNumber.setGenerateAccountNumber(generatedAccountNumber);
        System.out.println(accountNumber);

        accountNumberRepository.save(accountNumber);

        AccountNumberResponse accountNumberResponse = new AccountNumberResponse();
        accountNumberResponse.setAccountNumber(generatedAccountNumber);
        return accountNumberResponse;
    }

    private static String generateRandomNumber() {
        Random random = new Random();
        String randomNumber = String.format("%09d", random.nextInt(1000000000));
        return randomNumber;
    }

    private static int multiplyDigits(String bankCode, String randomDigits) {
        checkIfBankCodeIsThreeOrNotEmpty(bankCode);

        StringBuilder concatenatedDigits = new StringBuilder();
        concatenatedDigits.append(bankCode);
        concatenatedDigits.append(randomDigits);
        System.out.println(concatenatedDigits);

        int total = 0;
        for (int i = 0; i < concatenatedDigits.length(); i++) {
            int digit = Character.getNumericValue(concatenatedDigits.charAt(i));
            int multiplyValue = (i % 3 == 1) ? digit * 7 : digit * 3;
            total += multiplyValue;
        }

        System.out.println(randomDigits);
        System.out.println(total);
        return total;
    }

    private static void checkIfBankCodeIsThreeOrNotEmpty(String bankCode) {
        if (bankCode == null || bankCode.length() > 3) {
            throw new IllegalArgumentException("Bank code is empty or exceeds 3 characters");
        }
    }

    private static int divideTheTotalValueByModuleTen(int moduleNumber, int totalMultiplyValue) {
        int remainder = 0;
        if (moduleNumber > 0) {
            remainder = totalMultiplyValue % moduleNumber;
            System.out.println("remainder: " + remainder);
        }
        return remainder;
    }

    private static int subtractReminderValueFromModule(int moduleNumber, int totalMultiplyValue) {
        int remainder = divideTheTotalValueByModuleTen(moduleNumber, totalMultiplyValue);
        int subtractValue = remainder - moduleNumber;
        if (subtractValue > 9) {
            subtractValue = 0;
        }
        System.out.println("subtractValue: " + subtractValue);
        return subtractValue;
    }
}




























































































































































































































































































































