package com.example.homesvc.domain.mongo;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document("pricing_rules")
@CompoundIndex(name = "by_region_service_effective",
        def = "{'region':1, 'serviceType':1, 'effectiveFrom':-1}")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PricingRule {
    @Id
    private String id;
    @NotNull
    private Region region;
    @NotNull
    private ServiceType serviceType;
    @NotNull
    private BigDecimal baseRate;
    @NotNull
    private BigDecimal taxPct;
    @NotNull
    private BigDecimal surgePct;
    @NotNull
    private BigDecimal urgentFee;
    private boolean active;
    private Instant effectiveFrom;
    private String notes;
    private String updatedBy;
    private Instant updatedAt;
}
