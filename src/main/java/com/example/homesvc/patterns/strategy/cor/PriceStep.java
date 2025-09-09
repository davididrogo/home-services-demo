package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.Acc;
import com.example.homesvc.domain.Input;
import com.example.homesvc.domain.PricingRules;
import com.example.homesvc.service.RuleEngineService;

public interface PriceStep {
    void apply(
            Input in,
            PricingRules rules,
            Acc acc);

}
