package com.example.homesvc.repo;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.mongo.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProviderRepo extends MongoRepository<Provider, Long> {
    List<Provider> findByRegionAndSkillsContainsAndLicensedTrue(Region region, ServiceType skill);
}
