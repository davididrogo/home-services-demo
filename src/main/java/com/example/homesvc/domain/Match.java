package com.example.homesvc.domain;

import java.util.List;

public record Match(
        List<Long> providerIds,
        String notes
) {
}
