package com.example.homesvc.dto;
import com.example.homesvc.domain.enums.Region; import com.example.homesvc.domain.enums.ServiceType; import com.example.homesvc.domain.enums.UserTier;
import jakarta.validation.constraints.Future; import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime; import java.util.Map;
public class QuoteRequest {
  @NotNull public String userId;
  @NotNull public ServiceType serviceType;
  @NotNull public Region region;
  @NotNull @Future public LocalDateTime desiredAt;
  public boolean urgent;
  public UserTier tierOverride;
  public String voucherCode;
  public Map<String,String> extra;
}
