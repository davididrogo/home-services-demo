package com.example.homesvc.service;
import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.enums.UserTier;
import com.example.homesvc.domain.records.PricingRules;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Service
public class RuleEngineService {
  /*public record PricingRules(BigDecimal baseRate,BigDecimal taxPct,BigDecimal surgePct,BigDecimal urgentFee){}*/
  private final Map<String, PricingRules> rules = new HashMap<>();
  public RuleEngineService(){
    put(Region.NORTH, ServiceType.PLUMBING,80,0.12,0.10,25);
    put(Region.NORTH, ServiceType.ELECTRICAL,90,0.12,0.05,30);
    put(Region.SOUTH, ServiceType.PLUMBING,70,0.10,0.15,20);
    put(Region.EAST, ServiceType.HVAC,95,0.13,0.08,35);
    put(Region.WEST, ServiceType.APPLIANCE,60,0.11,0.20,15);
  }
  private void put(Region r,
                   ServiceType s,
                   double base,
                   double tax,
                   double surge,
                   double urgent){
    rules.put(key(r,s), new PricingRules(
                    BigDecimal.valueOf(base),
                    BigDecimal.valueOf(tax),
                    BigDecimal.valueOf(surge),
                    BigDecimal.valueOf(urgent)));
  }
  private String key(Region r, ServiceType s){
    return r.name() + "|" + s.name();
    //"NY|PLUMBING"
  }

  public PricingRules getRules(Region r, ServiceType s){
    return rules.getOrDefault(key(r,s), new PricingRules(
            new BigDecimal("75"),//base
            new BigDecimal("0.11"),//tax
            new BigDecimal("0.10"),//surge
            new BigDecimal("20")));//urgent
  }
  public BigDecimal discountForTier(UserTier tier){
    if(tier == null) return BigDecimal.ZERO;
    return switch (tier){
      case REGULAR -> BigDecimal.ZERO;
      case GOLD -> new BigDecimal("5");
      case PLATINUM -> new BigDecimal("10"); };
  }
  public boolean voucherValid(String code){
    return code != null &&
            (code.equalsIgnoreCase("SAVE5")
                    || code.equalsIgnoreCase("WELCOME10"));
  }
  public BigDecimal voucherDiscount(String code){
    if(code == null)
      return BigDecimal.ZERO;
    return switch (code.toUpperCase()){
      case "SAVE5" -> new BigDecimal("5");
      case "WELCOME10" -> new BigDecimal("10");
      default -> BigDecimal.ZERO; };
  }
}
