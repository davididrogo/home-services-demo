package com.example.homesvc.patterns.strategy;

import com.example.homesvc.domain.enums.PaymentMethod;
import com.example.homesvc.domain.records.PaymentResult;

import java.math.BigDecimal;

public interface PaymentStrategy {
    public PaymentMethod method();
    public PaymentResult charge(BigDecimal amount);
}
