package com.example.homesvc.domain;

import java.math.BigDecimal;

public record PricingRules(BigDecimal baseRate,
                           BigDecimal taxPct,
                           BigDecimal surgePct,
                           BigDecimal urgentFee) {
}
