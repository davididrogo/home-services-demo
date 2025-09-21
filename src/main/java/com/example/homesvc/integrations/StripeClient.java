package com.example.homesvc.integrations;

import java.math.BigDecimal;

public class StripeClient {
    public static final class ChargeResponse{
        public final boolean ok;
        public final String id;
        public ChargeResponse(boolean ok, String id){
            this.ok = ok;
            this.id = id;
        }
    }
    public ChargeResponse charge(BigDecimal amount, String currency, String customerId){
        boolean approved = amount.compareTo(new BigDecimal("2000")) <= 0;
        return new ChargeResponse(approved, approved ? "STRIPE_" + customerId :
                "STRIPE_DECLINED");
    }
}
