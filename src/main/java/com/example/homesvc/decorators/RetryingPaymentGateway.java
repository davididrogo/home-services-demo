package com.example.homesvc.decorators;

import com.example.homesvc.domain.Result;
import com.example.homesvc.ports.PaymentGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Currency;

public class RetryingPaymentGateway implements PaymentGateway {
    private static final Logger log = LoggerFactory.getLogger(RetryingPaymentGateway.class);
    private final PaymentGateway delegate;
    private final int maxRetries;
    private final long backoffMs;
    public RetryingPaymentGateway(PaymentGateway delegate,
                                  int maxRetries,
                                  Duration backoff){
        this.delegate = delegate;
        this.maxRetries = maxRetries;
        this.backoffMs = backoff.toMillis();
    }
    @Override
    public Result capture(BigDecimal amount, Currency currency, Long userId) {
        int attempt = 0;
        while(true){
            attempt++;
            try{
                Result r = delegate.capture(amount, currency, userId);
                if(r.success() || attempt > maxRetries)
                    return r;
                log.warn("payment retrying attemp={} code={}", attempt, r.code());
                Thread.sleep(backoffMs);
            }catch(RuntimeException e){
                if(attempt > maxRetries) throw e;
                log.warn("payment exception on attempt={}, retrying", attempt, e);
                try{
                    Thread.sleep(backoffMs);
                }catch(InterruptedException ie){
                    Thread.currentThread().interrupt();
                }
            }catch(InterruptedException ie){
                Thread.currentThread().interrupt();
                return new Result(false,"INTERRUMPED");
            }
        }
    }
}
