package com.phaselock.meterdataapplication.mapper.entity;

import com.phaselock.meterdataapplication.dto.entity.create.PaymentHistoryCreateDto;
import com.phaselock.meterdataapplication.dto.entity.read.PaymentHistoryReadDto;
import com.phaselock.meterdataapplication.entity.Account;
import com.phaselock.meterdataapplication.entity.PaymentHistory;
import org.springframework.stereotype.Component;

@Component
public class PaymentHistoryMapper {
    public PaymentHistoryReadDto map(PaymentHistory paymentHistory) {
        PaymentHistoryReadDto paymentHistoryReadDto = new PaymentHistoryReadDto();
        paymentHistoryReadDto.setDate(paymentHistory.getDate());
        paymentHistoryReadDto.setAmount(paymentHistory.getAmount());
        return paymentHistoryReadDto;
    }

    public PaymentHistory map(PaymentHistoryCreateDto paymentHistoryCreateDto) {
        PaymentHistory paymentHistory = new PaymentHistory();
        Account account = new Account();
        account.setId(paymentHistoryCreateDto.getAccountId());
        paymentHistory.setAccount(account);
        paymentHistory.setDate(paymentHistoryCreateDto.getDate());
        paymentHistory.setAmount(paymentHistoryCreateDto.getAmount());
        return paymentHistory;
    }
}
