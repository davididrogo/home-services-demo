package com.example.homesvc.repo;
import com.example.homesvc.domain.Booking; import java.util.*;
public interface BookingRepo {
    void save(Booking b);
    Optional<Booking> findById(Long id);
    java.util.List<Booking> findAll();
}
