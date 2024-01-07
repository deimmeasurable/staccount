package com.example.createaccountnumber.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountNumberRequest {
    private String bankCode;
    private int moduleValue;

}
