package com.phaselock.meterdataapplication.service;

import com.phaselock.meterdataapplication.dto.entity.create.PaymentHistoryCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.PaymentHistoryReadDto;
import com.phaselock.meterdataapplication.entity.PaymentHistory;
import com.phaselock.meterdataapplication.exception.NotFoundException;
import com.phaselock.meterdataapplication.mapper.entity.PaymentHistoryMapper;
import com.phaselock.meterdataapplication.repository.AccountRepository;
import com.phaselock.meterdataapplication.repository.PaymentHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final PaymentHistoryMapper paymentHistoryMapper;
    private final AccountRepository accountRepository;

    public PaymentHistoryReadDto savePaymentHistory(PaymentHistoryCreateDto paymentHistoryCreateDto) {
        return accountRepository.findById(paymentHistoryCreateDto.getAccountId())
                .map(account -> {
                    PaymentHistory paymentHistory = paymentHistoryMapper.map(paymentHistoryCreateDto);
                    paymentHistory.setAccount(account);
                    paymentHistoryRepository.save(paymentHistory);
                    return paymentHistoryMapper.map(paymentHistory);
                })
                .orElseThrow(() -> new NotFoundException("This account not exist"));
    }

    public List<PaymentHistoryReadDto> findAll() {
        return paymentHistoryRepository.findAll().stream()
                .map(paymentHistoryMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<PaymentHistoryReadDto> findById(Integer id) {
        return Optional.ofNullable(paymentHistoryRepository.findById(id)
                        .map(paymentHistoryMapper::map)
                .orElseThrow(() -> new NotFoundException("This payment not exist")));
    }

    public void deleteById(Integer id) {
        paymentHistoryRepository.findById(id)
                .ifPresent(account -> paymentHistoryRepository.deleteById(id));
    }
}
