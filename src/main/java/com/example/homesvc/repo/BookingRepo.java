package com.example.homesvc.repo;

import com.example.homesvc.domain.mongo.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends MongoRepository<Booking, Long> {

}
