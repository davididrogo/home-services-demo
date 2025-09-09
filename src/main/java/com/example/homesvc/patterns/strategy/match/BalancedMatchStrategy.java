package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.Match;
import com.example.homesvc.domain.MatchingAlgo;
import com.example.homesvc.domain.Provider;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class BalancedMatchStrategy implements MatchStrategy{
    @Override
    public MatchingAlgo id() {
        return MatchingAlgo.BALANCED;
    }

    @Override
    public List<Long> rank(List<Provider> candidates) {
        return candidates.stream()
                .sorted(Comparator.comparing(Provider::getReputation)
                        .reversed()
                        .thenComparing(Provider::getHourlyRate))
                .map(Provider::getId)
                .toList();
    }
}
