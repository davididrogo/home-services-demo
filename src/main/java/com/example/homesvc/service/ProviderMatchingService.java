package com.example.homesvc.service;

import com.example.homesvc.domain.enums.MatchingAlgo;
import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.records.Match;
import com.example.homesvc.patterns.strategy.match.MatchStrategy;
import com.example.homesvc.repo.ProviderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class ProviderMatchingService {
  private final ProviderRepo repo;
  private final Map<MatchingAlgo, MatchStrategy> strategies;
  public Match suggestProviders(Region region, ServiceType type, Map<String,String> extra){
    var candidates = repo.findByRegionAndSkillsContainsAndLicensedTrue(region, type)
            .stream()
            //.filter(Provider::licensed)
            .collect(Collectors.toList());
    //String algo = extra != null ? extra.getOrDefault("algo","CHEAPEST") : "CHEAPEST";
    //if(extra == null) return Review.CHEAPEST;
    //String algo = extra != null ? extra.get("algo") : null;
    var algo = parse(extra != null ? extra.get("algo") : null);
    var strat = strategies.getOrDefault(algo, strategies.get(MatchingAlgo.CHEAPEST));
    var ids = strat.rankCandidates(candidates);

    /*switch (algo.toUpperCase()){
      case "CHEAPEST" -> candidates.sort(Comparator.comparing(p -> p.getHourlyRate()));
      case "BEST_RATED" -> candidates.sort(Comparator.comparing(Provider::getReputation).reversed());
      case "BALANCED" -> candidates.sort(Comparator.comparing(Provider::getReputation).reversed().thenComparing(Provider::getHourlyRate));
      default -> candidates.sort(Comparator.comparing(p -> p.getHourlyRate()));
    }*/
    /*var ids = candidates
            .stream()
            .map(Provider::getId)
            .toList();*/
    return new Match(ids, "algo=" + algo + ", count= " + ids.size());
  }
  private MatchingAlgo parse(String name) {
    if(name == null) return null;
    try{
      return MatchingAlgo.valueOf(name.toUpperCase());
    }catch (IllegalArgumentException e){
      return null;
    }
  }
}
