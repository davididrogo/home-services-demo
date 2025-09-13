package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.domain.records.PricingRules;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class TimeOfDaySurchargeStep implements PriceStep{
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        if(in.when().getHour() >= 18 || in.when().getHour() < 7){
            acc.price = acc.price.add(new BigDecimal("15"));
            acc.surCharges = acc.surCharges.add(new BigDecimal("15"));
            acc.note("evening+15");
        }
    }
}
