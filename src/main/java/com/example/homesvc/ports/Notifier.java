package com.example.homesvc.ports;

public interface Notifier {
    void bookingConfirmed(Long userId, Long bookingId, Long providerId);
}
