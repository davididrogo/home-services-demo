package com.example.homesvc.domain.records;

import java.math.BigDecimal;

public record Calc(BigDecimal estimate,
                   BigDecimal taxes,
                   BigDecimal surcharges,
                   String notes) {
}
