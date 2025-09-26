package com.example.homesvc.dto;
import com.example.homesvc.domain.enums.BookingStatus;
import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal; import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BookingView {
  private Long id;
  private String userId;
  private String providerId;
  private ServiceType serviceType;
  private Region region;
  private LocalDateTime scheduledAt;
  private BookingStatus status;
  private BigDecimal quotedPrice;
  private BigDecimal finalPrice;
  private String notes;
}
