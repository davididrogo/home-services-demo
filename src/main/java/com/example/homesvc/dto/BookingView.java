package com.example.homesvc.dto;
import com.example.homesvc.domain.*; import java.math.BigDecimal; import java.time.LocalDateTime;
public class BookingView {
  public Long id;
  public Long userId;
  public Long providerId;
  public ServiceType serviceType;
  public Region region;
  public LocalDateTime scheduledAt;
  public BookingStatus status;
  public BigDecimal quotedPrice;
  public BigDecimal finalPrice;
  public String notes;
  public BookingView(){}
}
