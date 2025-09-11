package com.example.homesvc.service;
import com.example.homesvc.domain.PaymentMethod;
import com.example.homesvc.domain.PaymentResult;
import com.example.homesvc.patterns.strategy.PaymentStrategy;
import org.springframework.stereotype.Service; import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
@Service
public class PaymentService {
  private final Map<PaymentMethod, PaymentStrategy> strategies =
          new EnumMap<>(PaymentMethod.class);
  public PaymentService(List<PaymentStrategy> impls){
    for(var s : impls)
      strategies.put(s.method(), s);
  }
  /*public record PaymentResult(boolean success, String code){
  }*/
  private PaymentMethod validate(String method){
    if(method == null) return null;
    try{
      return PaymentMethod.valueOf(method);
    }catch(IllegalArgumentException e){
      return null;
    }
  }
  public PaymentResult charge(String method, BigDecimal amount){
    //if(method==null) return new PaymentResult(false, "NO_METHOD");
    //PaymentStrategy strategy;
    //if(method == null) return null;

    var s = validate(method);
    var m = s != null ? strategies.get(s) : null;
    return m.charge(amount);

    /*try{
      strategy = strategies.get(PaymentMethod.valueOf(method));
    }catch(IllegalArgumentException e) {
      return null;
    }
    if(strategy != null)
    return strategy.charge(amount);
    return null;

    /*switch (method.toUpperCase()){
      case "CARD" -> {
        boolean ok = new Random().nextInt(100) >= 10;
        return new PaymentResult(ok, ok? "APPROVED" : "DECLINED");
      }
      case "CASH" -> { return new PaymentResult(true, "PENDING_COLLECTION"); }
      case "WALLET" -> { boolean ok = amount.compareTo(new BigDecimal("200")) <= 0; return new PaymentResult(ok, ok? "DEBIT_OK" : "LIMIT_EXCEEDED"); }
      default -> { return new PaymentResult(false, "UNKNOWN_METHOD"); }
    }*/
  }
}
