package com.example.homesvc.integrations;

import java.util.Map;

public class AdyenClient {
    public static final class Auth{
        public final String pspRef;
        public final String status;
        public Auth(String pspRef, String status){
            this.pspRef = pspRef;
            this.status = status;
        }
    }
    public Auth authorize(Map<String, Object> payload){
        boolean ok = "EUR".equals(payload.get("currency"));
        return new Auth(ok ? "ADYEN_REF_42" : "ADYEN_FAIL_CCY", ok ? "AUTHORIZED" : "REFUSED");
    }
}
