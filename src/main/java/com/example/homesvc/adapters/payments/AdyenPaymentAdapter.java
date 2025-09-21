package com.example.homesvc.adapters.payments;

import com.example.homesvc.domain.records.Result;
import com.example.homesvc.integrations.AdyenClient;
import com.example.homesvc.ports.PaymentGateway;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

@Component
@Profile("eu")
public class AdyenPaymentAdapter implements PaymentGateway {
    private final AdyenClient client = new AdyenClient();
    @Override
    public Result capture(BigDecimal amount, Currency currency, String userId) {
        var auth = client.authorize(Map.of("amount", amount, "currency", currency
                .getCurrencyCode(), "userId", userId));
        boolean ok = "AUTHORIZED".equals(auth.status);
        return null;
    }
}
