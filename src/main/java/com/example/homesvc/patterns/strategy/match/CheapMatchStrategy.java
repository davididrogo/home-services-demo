package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.enums.MatchingAlgo;
import com.example.homesvc.domain.mongo.Provider;
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
    public List<String> rank(List<Provider> candidates) {
        return candidates.stream()
                .sorted(Comparator.comparing(Provider::getHourlyRate))
        .map(Provider::getId)
                .toList();

    }

}
