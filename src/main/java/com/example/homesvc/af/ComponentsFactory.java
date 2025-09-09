package com.example.homesvc.af;

import com.example.homesvc.ports.Notifier;
import com.example.homesvc.ports.PaymentGateway;

import java.util.Currency;

public interface ComponentsFactory {
    PaymentGateway paymentGateway();
    Notifier notifier();
    Currency currency();
}
