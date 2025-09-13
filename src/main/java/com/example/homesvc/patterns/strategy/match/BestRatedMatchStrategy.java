package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.enums.MatchingAlgo;
import com.example.homesvc.domain.mongo.Provider;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class BestRatedMatchStrategy implements MatchStrategy{
    @Override
    public MatchingAlgo id() {
        return MatchingAlgo.BEST_RATED;
    }

    @Override
    public List<Long> rank(List<Provider> candidates) {
        return candidates.stream()
                .sorted(Comparator.comparingInt((Provider p) -> p.reputation)
                        .reversed())
                .map(p -> p.id)
                .toList();
    }
}
