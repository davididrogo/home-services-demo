package com.example.homesvc.adapters.notify;

import com.example.homesvc.ports.Notifier;
import org.springframework.stereotype.Component;

@Component
public class SmsNotifierAdapter implements Notifier {
    @Override
    public void bookingConfirmed(Long userId, Long bookingId, Long providerId) {
        System.out.printf("[SMS] user=%d booking=%d provider=%d%n", userId, bookingId, providerId);
    }
}
