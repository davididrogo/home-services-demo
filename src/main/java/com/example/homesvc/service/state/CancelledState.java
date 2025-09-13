package com.example.homesvc.service.state;


import com.example.homesvc.domain.enums.BookingEvent;
import com.example.homesvc.domain.enums.BookingStatus;

public class CancelledState implements BookingState{
    @Override
    public BookingStatus status() {
        return null;
    }
    @Override
    public BookingStatus next(BookingEvent event) {
        throw new IllegalStateException("CANCELLED is terminal");
    }
}
