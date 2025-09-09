package com.example.homesvc.af;

import com.example.homesvc.adapters.notify.SmsNotifierAdapter;
import com.example.homesvc.adapters.payments.AdyenPaymentAdapter;
import com.example.homesvc.decorators.RetryingPaymentGateway;
import com.example.homesvc.decorators.payments.LoggingPaymentGateway;
import com.example.homesvc.ports.Notifier;
import com.example.homesvc.ports.PaymentGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Currency;

@Component
@Profile("eu")
public class EuComponentFactory implements ComponentsFactory{
    private final AdyenPaymentAdapter pay;
    private final SmsNotifierAdapter notif;
    public EuComponentFactory(AdyenPaymentAdapter pay, SmsNotifierAdapter notif){
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
        return null;
    }

    @Override
    public Currency currency() {
        return null;
    }
}
