package com.example.homesvc.service;
import com.example.homesvc.domain.*;
import com.example.homesvc.patterns.strategy.match.MatchStrategy;
import com.example.homesvc.repo.ProviderRepo; import org.springframework.stereotype.Service; import java.util.*; import java.util.stream.Collectors;
@Service
public class ProviderMatchingService {
  private final ProviderRepo repo;
  private Map<MatchingAlgo, MatchStrategy> strategies = new EnumMap<>(MatchingAlgo.class);
  public ProviderMatchingService(ProviderRepo repo, List<MatchStrategy> impls){
    this.repo=repo;
    impls.forEach(s -> strategies.put(s.id(), s));
  }
  /*public record Match(
          List<Long> providerIds,
          String notes){
  }*/
  public Match suggest(Region region, ServiceType type, Map<String,String> extra){
    var candidates = repo.findByRegionAndSkill(region, type)
            .stream()
            .filter(Provider::isLicensed)
            .collect(Collectors.toList());
    //String algo = extra != null ? extra.getOrDefault("algo","CHEAPEST") : "CHEAPEST";

    //if(extra == null) return Review.CHEAPEST;
    //String algo = extra != null ? extra.get("algo") : null;
    var algo = parse(extra != null ? extra.get("algo") : null);
    var strat = strategies.getOrDefault(algo, strategies.get(MatchingAlgo.CHEAPEST));
    var ids = strat.rank(candidates);

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
