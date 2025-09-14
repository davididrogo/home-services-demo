package com.example.homesvc.domain.records;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.enums.UserTier;

import java.time.LocalDateTime;

public record Input(
        Region region,
        ServiceType serviceType,
        boolean urgent,
        UserTier tier,
        String voucherCode,
        LocalDateTime when) {
}
