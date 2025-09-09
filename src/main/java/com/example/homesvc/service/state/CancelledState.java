package com.example.homesvc.service.state;


import com.example.homesvc.domain.BookingEvent;
import com.example.homesvc.domain.BookingStatus;

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
