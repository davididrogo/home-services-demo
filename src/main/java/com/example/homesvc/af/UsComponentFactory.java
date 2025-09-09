package com.example.homesvc.af;

import com.example.homesvc.adapters.notify.EmailNotifierAdapter;
import com.example.homesvc.adapters.payments.StripePaymentAdapter;
import com.example.homesvc.decorators.RetryingPaymentGateway;
import com.example.homesvc.decorators.payments.LoggingPaymentGateway;
import com.example.homesvc.ports.Notifier;
import com.example.homesvc.ports.PaymentGateway;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Currency;

@Component
@Primary//("us")
public class UsComponentFactory implements ComponentsFactory{
    private final StripePaymentAdapter pay;
    private final EmailNotifierAdapter notif;
    public UsComponentFactory(StripePaymentAdapter pay, EmailNotifierAdapter notif) {
        this.pay = pay;
        this.notif = notif;
    }
    @Override
    public PaymentGateway paymentGateway() {
        var withRetry = new RetryingPaymentGateway(pay, 1, Duration.ofMillis(50));
        return new LoggingPaymentGateway(withRetry);
    }
    @Override
    public Notifier notifier() {
        return notif;
    }
    @Override
    public Currency currency() {
        return Currency.getInstance("USD");
    }
}
