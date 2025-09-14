package com.example.homesvc.patterns.strategy.cor;

import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.domain.records.PricingRules;
import com.example.homesvc.service.RuleEngineService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(70)
public class VoucherDiscountStep implements PriceStep{
    private final RuleEngineService rulesService;

    public VoucherDiscountStep(RuleEngineService ruleEngineService) {
        this.rulesService = ruleEngineService;
    }
    @Override
    public void apply(Input in, PricingRules rules, Acc acc) {
        if(rulesService.voucherValid(in.voucherCode())) {

            var disc = rulesService.voucherDiscount(in.voucherCode());
            acc.price = acc.price.subtract(disc);
            acc.note("voucherDisc=" + disc);
        }
    }
}
