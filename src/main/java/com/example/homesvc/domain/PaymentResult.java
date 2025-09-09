package com.example.homesvc.domain;

public record PaymentResult(
        boolean success,
        String code) {
}
