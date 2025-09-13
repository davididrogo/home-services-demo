package com.example.homesvc.domain.records;

public record PaymentResult(
        boolean success,
        String code) {
}
