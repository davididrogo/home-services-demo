package com.example.homesvc.dto;
import com.example.homesvc.domain.enums.Region; import com.example.homesvc.domain.enums.ServiceType;
import jakarta.validation.constraints.Future; import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateBookingRequest {
  @NotNull public String userId;
  public String preferredProviderId;
  @NotNull public ServiceType serviceType;
  @NotNull public Region region;
  @NotNull @Future public LocalDateTime scheduledAt;
  @NotNull public String paymentMethod; // CARD | CASH | WALLET
  public boolean highPriority;
  public String voucherCode;
}
