package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.AccountCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.AccountReadDto;
import com.phaselock.meterdataapplication.entity.Account;
import com.phaselock.meterdataapplication.entity.Apartment;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountReadDto map(Account account) {
        AccountReadDto accountReadDto = new AccountReadDto();
        accountReadDto.setAccountNumber(account.getAccountNumber());
        return accountReadDto;
    }

    public Account map(AccountCreateDto accountCreateDto) {
        Account account = new Account();
        Apartment apartment = new Apartment();
        apartment.setId(accountCreateDto.getApartmentId());
        account.setAccountNumber(accountCreateDto.getAccountNumber());
        account.setApartment(apartment);
        return account;
    }
}
