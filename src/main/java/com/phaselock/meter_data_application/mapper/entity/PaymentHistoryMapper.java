package com.phaselock.meter_data_application.mapper.entity;

import com.phaselock.meter_data_application.dto.entity.create.PaymentHistoryCreateDto;
import com.phaselock.meter_data_application.dto.entity.read.PaymentHistoryReadDto;
import com.phaselock.meter_data_application.entity.Account;
import com.phaselock.meter_data_application.entity.PaymentHistory;
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
