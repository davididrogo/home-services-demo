package com.example.homesvc.domain;

import java.time.LocalDateTime;

public record Input(
        Region region,
        ServiceType serviceType,
        boolean urgent,
        UserTier tier,
        String voucherCode,
        LocalDateTime when) {
}
