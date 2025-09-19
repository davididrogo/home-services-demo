package com.example.homesvc.domain.mongo;

import com.example.homesvc.domain.enums.BookingStatus;
import com.example.homesvc.domain.enums.Region;
import com.example.homesvc.domain.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document("bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    public String id;
    @Indexed(unique = true)
    private Long number;
    @Indexed
    public Long userId;
    @Indexed
    public String providerId;
    public ServiceType serviceType;
    public Region region;
    public LocalDateTime scheduledAt;
    public BookingStatus status;
    public BigDecimal quotedPrice;
    public BigDecimal finalPrice;
    public String notes;
}
