package com.example.homesvc.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendBookingConfirmation(String userId, String bookingId, String providerId){
        System.out.printf("[NOTIFY] user=%d booking=%s provider=%s%n", userId, bookingId, providerId);
    }
}
