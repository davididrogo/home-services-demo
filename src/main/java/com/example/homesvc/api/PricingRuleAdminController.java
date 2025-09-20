package com.example.homesvc.api;

import com.example.homesvc.domain.mongo.PricingRule;
import com.example.homesvc.repo.PricingRuleRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/admin/rules")
@RequiredArgsConstructor
public class PricingRuleAdminController {
    private final PricingRuleRepository repo;
    private final CacheManager cacheManager;

    @GetMapping
    public List<PricingRule> list(){
        return repo.findAll();
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public PricingRule create(@Valid @RequestBody PricingRule pr){
        pr.setId(null);
        pr.setUpdatedAt(Instant.now());
        return saveAndBust(pr);
    }
    @PatchMapping("/{id}/activate")
    public PricingRule activate(@PathVariable String id){
        var pr = repo.findById(id).orElseThrow();
        repo.findAll()
                .stream()
                .filter(x -> x.getRegion() == pr.getRegion()
                && x.getServiceType() == pr.getServiceType())
                .forEach(x -> {x.setActive(false);
                repo.save(x); });
        pr.setActive(true);
        pr.setUpdatedAt(Instant.now());
        return saveAndBust(pr);
    }
    private PricingRule saveAndBust(PricingRule pr){
        var saved = repo.save(pr);
        var key = pr.getRegion().name() + "|" + pr.getServiceType().name();
        var cache = cacheManager.getCache("pricingRules");
        if (cache != null)
            cache.evict(key);
        return saved;
    }
}
