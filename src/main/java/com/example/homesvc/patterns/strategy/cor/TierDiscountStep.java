package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.Acc;
import com.example.homesvc.domain.Input;
import com.example.homesvc.domain.PricingRules;
import com.example.homesvc.service.RuleEngineService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(60)
public class TierDiscountStep implements PriceStep{
    private RuleEngineService engineService;
    public TierDiscountStep(RuleEngineService engineService) {
        this.engineService = engineService;
    }
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        BigDecimal tierDisc = engineService.discountForTier(in.tier());
        if(tierDisc.signum() != 0){
            acc.price = acc.price.subtract(tierDisc);
            acc.note("tierDesc=" + tierDisc);
        }

    }
}
