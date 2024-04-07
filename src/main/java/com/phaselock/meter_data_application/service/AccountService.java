package com.phaselock.meter_data_application.service;

import com.phaselock.meter_data_application.dto.entity.create.AccountCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.AccountReadDto;
import com.phaselock.meter_data_application.exception.NotFoundException;
import com.phaselock.meter_data_application.mapper.entity.AccountMapper;
import com.phaselock.meter_data_application.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountReadDto saveAccount(AccountCreateDto accountCreateDto) {
        return Optional.of(accountCreateDto)
                .map(accountMapper::map)
                .map(accountRepository::save)
                .map(accountMapper::map)
                .orElseThrow();
    }

    public List<AccountReadDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<AccountReadDto> findById(Integer id) {
        return Optional.ofNullable(accountRepository.findById(id)
                .map(accountMapper::map)
                .orElseThrow(() -> new NotFoundException("This account not exist")));
    }

    public void deleteById(Integer id) {
        accountRepository.findById(id)
                .ifPresent(account -> accountRepository.deleteById(id));
    }
}
