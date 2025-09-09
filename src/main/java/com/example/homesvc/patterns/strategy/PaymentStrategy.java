package com.example.homesvc.patterns.strategy;

import com.example.homesvc.domain.PaymentMethod;
import com.example.homesvc.domain.PaymentResult;
import com.example.homesvc.service.PaymentService;

import java.math.BigDecimal;

public interface PaymentStrategy {
    public PaymentMethod method();
    public PaymentResult charge(BigDecimal amount);
}
