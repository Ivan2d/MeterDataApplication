package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.AccountCreateDto;
import com.phaselock.meterdataapplication.entity.Account;
import com.phaselock.meterdataapplication.entity.Apartment;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountCreateDto map(Account account) {
        AccountCreateDto accountCreateDto = new AccountCreateDto();
        accountCreateDto.setAccountNumber(account.getAccountNumber());
        accountCreateDto.setBalance(account.getBalance());
        accountCreateDto.setApartmentId(account.getApartment().getId());
        return accountCreateDto;
    }

    public Account map(AccountCreateDto accountCreateDto) {
        Account account = new Account();
        Apartment apartment = new Apartment();
        apartment.setId(accountCreateDto.getApartmentId());
        account.setAccountNumber(accountCreateDto.getAccountNumber());
        account.setBalance(accountCreateDto.getBalance());
        account.setApartment(apartment);
        return account;
    }
}
