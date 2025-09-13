package com.example.homesvc.service.state;

import com.example.homesvc.domain.enums.BookingEvent;
import com.example.homesvc.domain.enums.BookingStatus;

public class CompletedState implements BookingState{
    @Override
    public BookingStatus status() {
        return BookingStatus.COMPLETED;
    }
    @Override
    public BookingStatus next(BookingEvent event) {
        throw new IllegalStateException("COMPLETED is terminal");
    }
}
