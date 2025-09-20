package com.example.homesvc.infra.mongo.seed;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.mongo.PricingRule;
import com.example.homesvc.repo.PricingRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
@Profile({"dev","seed"})
@RequiredArgsConstructor
public class PricingRuleSeeder implements ApplicationRunner {

    private final PricingRuleRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(repo.count() > 0 ) return;
        List<PricingRule> rules = List.of(
                rule(Region.NORTH, ServiceType.PLUMBING, 80, .12, .10, 25),
                rule(Region.NORTH, ServiceType.ELECTRICAL,90, .12, .05, 30),
                rule(Region.SOUTH, ServiceType.PLUMBING, 70, .10, .15, 20),
                rule(Region.EAST,  ServiceType.HVAC,     95, .13, .08, 35),
                rule(Region.WEST,  ServiceType.APPLIANCE,60, .11, .20, 15)
        );
        repo.saveAll(rules);
    }
    private PricingRule rule(Region r, ServiceType s, double base,
                             double tax, double surge, double urgent){
        return PricingRule.builder()
                .region(r)
                .serviceType(s)
                .baseRate(BigDecimal.valueOf(base))
                .taxPct(BigDecimal.valueOf(tax))
                .surgePct(BigDecimal.valueOf(surge))
                .urgentFee(BigDecimal.valueOf(urgent))
                .active(true)
                .effectiveFrom(Instant.now())
                .notes("seed")
                .updatedBy("seeder")
                .updatedAt(Instant.now())
                .build();
    }
}
