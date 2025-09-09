package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.MatchingAlgo;
import com.example.homesvc.domain.Provider;
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
                .sorted(Comparator.comparing(Provider::getReputation).reversed())
                .map(Provider::getId)
                .toList();
    }
}
