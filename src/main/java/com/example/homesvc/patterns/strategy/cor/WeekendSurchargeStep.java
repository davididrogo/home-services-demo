package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.Acc;
import com.example.homesvc.domain.Input;
import com.example.homesvc.domain.PricingRules;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.DayOfWeek;

@Component
public class WeekendSurchargeStep implements PriceStep{
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        DayOfWeek d = in.when().getDayOfWeek();
        if(d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY){
            acc.price = acc.price.add(new BigDecimal("20"));
            acc.surCharges = acc.surCharges.add(new BigDecimal("20"));
            acc.note("weekend+20");
        }

    }
}
