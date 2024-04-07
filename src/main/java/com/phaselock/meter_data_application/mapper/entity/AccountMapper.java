package com.phaselock.meter_data_application.mapper.entity;

import com.phaselock.meter_data_application.dto.entity.create.AccountCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.AccountReadDto;
import com.phaselock.meter_data_application.entity.Account;
import com.phaselock.meter_data_application.entity.Apartment;
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
