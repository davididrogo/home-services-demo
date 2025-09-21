package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.enums.MatchingAlgo;
import com.example.homesvc.domain.mongo.Provider;

import java.util.List;

public interface MatchStrategy {
    MatchingAlgo id();
    List<String> rankCandidates(List<Provider> candidates);
}
