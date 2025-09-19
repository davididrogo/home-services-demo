package com.example.homesvc.repo;
import com.example.homesvc.domain.mongo.Booking;
import com.example.homesvc.ports.BookingStore;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Repository
@Profile("mem")
public class InMemoryBookingRepo implements BookingStore {
  private final Map<String, Booking> store = new ConcurrentHashMap<>();
  public void save(Booking b){
    store.put(b.id, b);
  }
  public Optional<Booking> findById(Long id){
    return Optional.ofNullable(store.get(id));
  }
  public List<Booking> findAll(){
    return new ArrayList<>(store.values());
  }
}
