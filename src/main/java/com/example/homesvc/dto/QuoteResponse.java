package com.example.homesvc.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class QuoteResponse {
  public BigDecimal estimatedPrice;
  public BigDecimal taxes;
  public BigDecimal surcharges;
  public String pricingNotes;
  public List<Long> suggestedProviderIds;
  public String matchingNotes;
  public LocalDateTime quoteAt;
  public QuoteResponse(){}
  public QuoteResponse(BigDecimal e, BigDecimal t, BigDecimal s, String pn, List<Long> p, String mn, LocalDateTime qa){
    this.estimatedPrice = e;
    this.taxes = t;
    this.surcharges = s;
    this.pricingNotes = pn;
    this.suggestedProviderIds = p;
    this.matchingNotes = mn;
    this.quoteAt = qa;
  }
}
