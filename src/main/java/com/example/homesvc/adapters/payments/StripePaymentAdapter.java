package com.example.homesvc.adapters.payments;

import com.example.homesvc.domain.records.Result;
import com.example.homesvc.integrations.StripeClient;
import com.example.homesvc.ports.PaymentGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

@Component
public class StripePaymentAdapter implements PaymentGateway {
    private final StripeClient client = new StripeClient();
    @Override
    public Result capture(BigDecimal amount, Currency currency, Long userId) {
        var cents = amount.movePointRight(2).longValueExact();
        var resp = client.charge(amount, currency.getCurrencyCode(), userId);
        return new Result(resp.ok, resp.id);
    }
}
