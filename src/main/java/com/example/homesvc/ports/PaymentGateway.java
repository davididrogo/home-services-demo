package com.example.homesvc.ports;

import com.example.homesvc.domain.Result;

import java.math.BigDecimal;
import java.util.Currency;

public interface PaymentGateway {
   /* record Result(
            boolean success,
            String code){};*/
    Result capture(BigDecimal amount, Currency currency, Long userId);
}
