package com.example.homesvc.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendBookingConfirmation(Long userId, Long bookingId, Long providerId){
        System.out.printf("[NOTIFY] user=%d booking=%d provider=%d%n", userId, bookingId, providerId);
    }
}
