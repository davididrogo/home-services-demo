package com.example.homesvc.config;

import com.example.homesvc.decorators.RetryingPaymentGateway;
import com.example.homesvc.domain.MatchingAlgo;
import com.example.homesvc.patterns.strategy.match.MatchStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class MatchStrategyConfig {
    private static final Logger log = LoggerFactory.getLogger(MatchStrategyConfig.class);
    @Bean
    Map<MatchingAlgo, MatchStrategy> strategies(List<MatchStrategy> impls) {
        log.info("MatchStrategyConfig has been called");
        impls.forEach(s -> log.info("strategy bean: {} -> id={}", s.getClass().getName(), s.id()));
        return impls.stream().collect(
                Collectors.toMap(MatchStrategy::id, Function.identity(),
                        (a, b) -> a, () -> new EnumMap<>(MatchingAlgo.class))
        );
    }
}
