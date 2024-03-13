package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.AccountCreateDto;
import com.phaselock.meterdataapplication.entity.Account;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.AccountMapper;
import com.phaselock.meterdataapplication.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountCreateDto saveAccount(AccountCreateDto accountCreateDto) {
        return Optional.of(accountCreateDto)
                .map(accountMapper::map)
                .map(accountRepository::save)
                .map(accountMapper::map)
                .orElseThrow();
    }

    public Iterable<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Integer id) {
        return Optional.ofNullable(accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("This account not exist")));
    }

    public void deleteById(Integer id) {
        accountRepository.findById(id)
                .ifPresent(account -> accountRepository.deleteById(id));
    }
}
