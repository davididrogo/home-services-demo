package com.example.homesvc.service.state;

import com.example.homesvc.domain.enums.BookingEvent;
import com.example.homesvc.domain.enums.BookingStatus;

public interface BookingState {
    BookingStatus status();
    BookingStatus next(BookingEvent event);
}
