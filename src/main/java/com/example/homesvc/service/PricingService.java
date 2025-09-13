package com.example.homesvc.service;

import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import com.example.homesvc.domain.enums.UserTier;
import com.example.homesvc.domain.mongo.Acc;
import com.example.homesvc.domain.records.Calc;
import com.example.homesvc.domain.records.Input;
import com.example.homesvc.patterns.strategy.cor.PriceStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.*;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PricingService {
  private final RuleEngineService rules;
  private final List<PriceStep> steps;
  public Calc estimate(Region region,
                       ServiceType type,
                       boolean urgent,
                       UserTier tier,
                       String voucher,
                       LocalDateTime desiredAt){
    var r = rules.getRules(region, type);

    var in = new Input(region, type, urgent, tier, voucher, desiredAt);
    var acc = new Acc();

    for(var s : steps) {
      s.apply(in, r, acc);
    }
    var estimate = acc.price/*add as it is BigDecimal*/
            .add(acc.taxes)
            .setScale(2, RoundingMode.HALF_UP);

    /*BigDecimal price = r.baseRate();

    if(desiredAt.getHour() >= 18 || desiredAt.getHour() < 7){
      price = price.add(new BigDecimal("15"));
    }
    switch (desiredAt.getDayOfWeek()){
      case SATURDAY, SUNDAY -> price = price.add(new BigDecimal("20")); default -> {}
    }
    price = price.add(r.baseRate().multiply(r.surgePct()));
    BigDecimal sur = BigDecimal.ZERO;
    if(urgent){
      sur = sur.add(r.urgentFee());
      price = price.add(r.urgentFee());
    }
    BigDecimal tierDisc = rules.discountForTier(tier);
    if(tierDisc.signum() != 0){
      price = price.subtract(tierDisc);
    }
    BigDecimal voucherDisc = BigDecimal.ZERO;
    if(rules.voucherValid(voucher)){
      voucherDisc = rules.voucherDiscount(voucher);
      price = price.subtract(voucherDisc);
    }
    BigDecimal taxes = price.multiply(r.taxPct()).setScale(2, RoundingMode.HALF_UP);
    BigDecimal estimate = price.add(taxes).setScale(2, RoundingMode.HALF_UP);
    String notes = "rule="+region+"-"+type+", tierDisc="+tierDisc+", voucherDisc="+voucherDisc+", time="+desiredAt+", urgent="+urgent;
*/
    return new Calc(estimate, acc.taxes, acc.surCharges, acc.notes.toString());
  }
}
