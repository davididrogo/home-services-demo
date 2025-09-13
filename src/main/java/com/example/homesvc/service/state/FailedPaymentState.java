package com.example.homesvc.service.state;

import com.example.homesvc.domain.enums.BookingEvent;
import com.example.homesvc.domain.enums.BookingStatus;

public class FailedPaymentState implements BookingState{
    @Override
    public BookingStatus status() {
        return BookingStatus.FAILED_PAYMENT;
    }
    @Override
    public BookingStatus next(BookingEvent event) {
        throw new IllegalStateException("FAILED_PAYMENT is terminal");
    }
}
