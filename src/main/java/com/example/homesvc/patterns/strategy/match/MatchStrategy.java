package com.example.homesvc.patterns.strategy.match;

import com.example.homesvc.domain.Provider;
import com.example.homesvc.domain.MatchingAlgo;

import java.util.List;

public interface MatchStrategy {
    MatchingAlgo id();
    List<Long> rank(List<Provider> candidates);
}
