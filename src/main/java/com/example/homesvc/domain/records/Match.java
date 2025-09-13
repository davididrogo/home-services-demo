package com.example.homesvc.domain.records;

import java.util.List;

public record Match(
        List<Long> providerIds,
        String notes
) {
}
