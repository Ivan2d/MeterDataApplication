package com.phaselock.meterdataapplication.controller;

import com.phaselock.meterdataapplication.dto.entity.AccountCreateDto;
import com.phaselock.meterdataapplication.entity.Account;
import com.phaselock.meterdataapplication.exception.not_found_exception.NotFoundException;
import com.phaselock.meterdataapplication.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@SecurityRequirement(name = "Keycloak")
public class AccountRestController {
    private final AccountService accountService;

    @PostMapping("")
    @PreAuthorize("hasRole('user') or hasRole('owner') or hasRole('admin')")
    public AccountCreateDto addAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return accountService.saveAccount(accountCreateDto);
    }

    @GetMapping("")
    @PreAuthorize("hasRole('admin')")
    public Iterable<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Optional<Account> getAccountById(@PathVariable("id") Integer id) throws NotFoundException {
        return accountService.findById(id);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public void deleteAccountById(@PathVariable("id") Integer id) {
        accountService.deleteById(id);
    }
}
