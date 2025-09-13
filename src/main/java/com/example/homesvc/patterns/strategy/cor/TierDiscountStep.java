package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.domain.records.PricingRules;
import com.example.homesvc.service.RuleEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Order(60)
public class TierDiscountStep implements PriceStep{
    private final RuleEngineService engineService;
    /*public TierDiscountStep(RuleEngineService engineService) {
        this.engineService = engineService;
    }*/
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        BigDecimal tierDisc = engineService.discountForTier(in.tier());
        if(tierDisc.signum() != 0){
            acc.price = acc.price.subtract(tierDisc);
            acc.note("tierDesc=" + tierDisc);
        }

    }
}
