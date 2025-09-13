package com.example.homesvc.service.state;

import com.example.homesvc.domain.enums.BookingEvent;
import com.example.homesvc.domain.enums.BookingStatus;

public class InProgressState implements BookingState{
    @Override
    public BookingStatus status() {
        return BookingStatus.IN_PROGRESS;
    }
    @Override
    public BookingStatus next(BookingEvent event) {
        return switch (event){
            case COMPLETE_WORK -> BookingStatus.COMPLETED;
            case CANCEL -> BookingStatus.CANCELLED;
            default -> throw new IllegalStateException("Invalid transition from " +
                    "IN_PROGRESS " + event);
        };
    }
}
