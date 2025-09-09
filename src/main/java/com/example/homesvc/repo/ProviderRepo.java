package com.example.homesvc.repo;

import com.example.homesvc.domain.*;
import java.util.*;
public interface ProviderRepo {
    List<Provider> findAll();
    void save(Provider p);
    List<Provider> findByRegionAndSkill(Region r, ServiceType s);
}
