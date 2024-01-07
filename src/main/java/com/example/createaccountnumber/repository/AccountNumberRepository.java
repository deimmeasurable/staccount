package com.example.createaccountnumber.repository;

import com.example.createaccountnumber.model.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AccountNumberRepository extends JpaRepository<AccountNumber,Long> {

}
