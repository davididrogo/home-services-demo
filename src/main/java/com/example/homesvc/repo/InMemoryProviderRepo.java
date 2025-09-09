package com.example.homesvc.repo;
import com.example.homesvc.domain.*;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
@Repository
public class InMemoryProviderRepo implements ProviderRepo {
  private final List<Provider> providers = new CopyOnWriteArrayList<>();
  public List<Provider> findAll(){
    return new ArrayList<>(providers);
  }
  public void save(Provider p){
    providers.add(p);
  }
  public java.util.List<Provider> findByRegionAndSkill(Region r, ServiceType s){
    return providers.stream()
            .filter(p -> p.getRegion() == r && p.getSkills().contains(s))
            .collect(Collectors.toList());
  }
}
