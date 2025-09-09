package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.Acc;
import com.example.homesvc.domain.Input;
import com.example.homesvc.domain.PricingRules;

public class UrgentFeeStep implements PriceStep{

    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        if(in.urgent()){
            acc.price = acc.price.add(rules.urgentFee());
            acc.surCharges = acc.surCharges.add(rules.urgentFee());
            acc.note("urgent=" + rules.urgentFee());
        }

    }
}
