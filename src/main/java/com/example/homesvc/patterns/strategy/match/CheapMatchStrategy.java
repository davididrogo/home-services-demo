package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.Provider;
import com.example.homesvc.domain.MatchingAlgo;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class CheapMatchStrategy implements MatchStrategy{

    @Override
    public MatchingAlgo id() {
        return MatchingAlgo.CHEAPEST;
    }

    @Override
    public List<Long> rank(List<Provider> candidates) {
        return candidates.stream()
                .sorted(Comparator.comparing(Provider::getHourlyRate))
        .map(Provider::getId)
                .toList();

    }

}
