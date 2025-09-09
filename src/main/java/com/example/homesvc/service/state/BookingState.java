package com.example.homesvc.service.state;

import com.example.homesvc.domain.BookingEvent;
import com.example.homesvc.domain.BookingStatus;

public interface BookingState {
    BookingStatus status();
    BookingStatus next(BookingEvent event);
}
