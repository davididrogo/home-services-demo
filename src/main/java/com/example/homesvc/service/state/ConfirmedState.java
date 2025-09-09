package com.example.homesvc.service.state;

import com.example.homesvc.domain.BookingEvent;
import com.example.homesvc.domain.BookingStatus;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedState implements BookingState{
    @Override
    public BookingStatus status() {
        return BookingStatus.CONFIRMED;
    }
    @Override
    public BookingStatus next(BookingEvent event) {
        return switch (event){
            case START_WORK -> BookingStatus.IN_PROGRESS;
            case CANCEL -> BookingStatus.CANCELLED;
            default -> throw new IllegalStateException("Invalid transition from CONFIRMED via " +
                    event);
        };
    }
}
