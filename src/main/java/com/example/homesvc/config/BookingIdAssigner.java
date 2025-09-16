package com.example.homesvc.config;

import com.example.homesvc.domain.mongo.Booking;
import com.example.homesvc.infra.mongo.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingIdAssigner implements ApplicationListener<BeforeConvertEvent<Booking>> {
    private final SequenceService seq;
    public void onApplicationEvent(BeforeConvertEvent<Booking> e) {
        var b = e.getSource();
        if(b.getNumber() == null )
            b.setNumber(seq.next("booking"));
    }
}
