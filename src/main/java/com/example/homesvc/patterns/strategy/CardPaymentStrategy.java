package com.example.homesvc.patterns.strategy;

import com.example.homesvc.domain.PaymentMethod;
import com.example.homesvc.domain.PaymentResult;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class CardPaymentStrategy implements PaymentStrategy{
    @Override
    public PaymentMethod method() {
        return PaymentMethod.CARD;
    }

    @Override
    public PaymentResult charge(BigDecimal amount) {
        boolean ok = ThreadLocalRandom.current().nextInt(100) >= 10;
        return new PaymentResult(ok, ok ? "APPROVED" : "DECLINED");
    }
}
