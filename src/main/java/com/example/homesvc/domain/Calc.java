package com.example.homesvc.domain;

import java.math.BigDecimal;

public record Calc(BigDecimal estimate,
                   BigDecimal taxes,
                   BigDecimal surcharges,
                   String notes) {
}
