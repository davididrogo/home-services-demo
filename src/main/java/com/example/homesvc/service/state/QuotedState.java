package com.example.homesvc.service.state;

import com.example.homesvc.domain.BookingEvent;
import com.example.homesvc.domain.BookingStatus;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.stereotype.Component;

@Component
public class QuotedState implements BookingState{
    @Override
    public BookingStatus status() {
        return BookingStatus.QUOTED;
    }
    @Override
    public BookingStatus next(BookingEvent event) {
        return switch(event) {
            case PAYMENT_AUTHORIZED -> BookingStatus.CONFIRMED;
            case PAYMENT_FAILED -> BookingStatus.FAILED_PAYMENT;
            case CANCEL -> BookingStatus.CANCELLED;
            default -> throw new IllegalStateException("Invalid transition from QUOTE via " +
                    event);
        };
    }
}
