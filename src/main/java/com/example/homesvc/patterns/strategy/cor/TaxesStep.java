package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.domain.records.PricingRules;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
@Order(80)
public class TaxesStep implements PriceStep{
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        acc.taxes = acc.price
                .multiply(rules.taxPct())
                .setScale(2, RoundingMode.HALF_UP);
        acc.note("Tax = " + rules.taxPct());
    }
}
