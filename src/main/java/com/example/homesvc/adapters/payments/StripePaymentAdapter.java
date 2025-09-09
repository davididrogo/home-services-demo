package com.example.homesvc.adapters.payments;

import com.example.homesvc.domain.Result;
import com.example.homesvc.ports.PaymentGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

@Component
public class StripePaymentAdapter implements PaymentGateway {
    @Override
    public Result capture(BigDecimal amount, Currency currency, Long userId) {
        return null;
    }
}
