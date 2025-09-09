package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.Acc;
import com.example.homesvc.domain.Input;
import com.example.homesvc.domain.PricingRules;
import org.springframework.stereotype.Component;

@Component
public class RegionalSurgeStep implements PriceStep{

    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        var bump = rules.baseRate().multiply(rules.surgePct());
        acc.price = acc.price.add(bump);
        acc.note("surge="+rules.surgePct());
    }
}
