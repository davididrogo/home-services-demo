package com.example.homesvc.repo;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.mongo.PricingRule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PricingRuleRepository extends MongoRepository<PricingRule, String> {
    Optional<PricingRule> findTopByRegionAndServiceTypeAndActiveTrueOrderByEffectiveFromDesc(
            Region region, ServiceType serviceType);
}
