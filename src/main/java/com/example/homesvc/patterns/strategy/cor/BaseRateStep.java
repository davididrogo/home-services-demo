package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.domain.records.PricingRules;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(10)
public class BaseRateStep implements PriceStep{
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        acc.price = rules.baseRate();
        acc.note("base= " + rules.baseRate());
    }
}
