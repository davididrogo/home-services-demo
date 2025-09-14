package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.domain.records.PricingRules;

public interface PriceStep {
    void apply(
            Input in,
            PricingRules rules,
            Acc acc);

}
