package com.example.homesvc.ports;

import com.example.homesvc.domain.mongo.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingStore {
    void save(Booking b);
    Optional<Booking> findById(Long id);
    List<Booking> findAll();
}
