package com.example.homesvc.dto;
import com.example.homesvc.domain.enums.Region; import com.example.homesvc.domain.enums.ServiceType;
import jakarta.validation.constraints.Future; import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
public class CreateBookingRequest {
  @NotNull public Long userId;
  public Long preferredProviderId;
  @NotNull public ServiceType serviceType;
  @NotNull public Region region;
  @NotNull @Future public LocalDateTime scheduledAt;
  @NotNull public String paymentMethod; // CARD | CASH | WALLET
  public boolean highPriority;
  public String voucherCode;
}
