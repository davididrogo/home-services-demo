package com.example.homesvc.decorators.payments;

import com.example.homesvc.domain.Result;
import com.example.homesvc.ports.PaymentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

public class LoggingPaymentGateway implements PaymentGateway {
    private static final Logger log = LoggerFactory.getLogger(LoggingPaymentGateway.class);
    public final PaymentGateway delegate;
    public LoggingPaymentGateway(PaymentGateway delegate){
        this.delegate = delegate;
    }
    @Override
    public Result capture(BigDecimal amount, Currency currency, Long userId) {
        long t0 = System.nanoTime();
        try{
            Result r = delegate.capture(amount, currency, userId);
            long ms = (System.nanoTime() - t0) / 1_000_000;
            log.info("payment {} amount={} {} user={} took={}ms code={}",
                    r.success() ? "OK" : "FAIL", amount, currency, userId, ms, r.code());
            return r;
        }catch(RuntimeException e){
            long ms = (System.nanoTime() - t0) / 1_000_000;
            log.error("payment EX amount ={} {} user={} took={}ms ex={}",
                    amount, currency, userId, ms, e.toString());
            throw e;
        }
        //return null;
    }
}
