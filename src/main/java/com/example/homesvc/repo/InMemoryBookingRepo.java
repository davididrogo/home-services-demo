package com.example.homesvc.repo;
import com.example.homesvc.domain.Booking;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class InMemoryBookingRepo implements BookingRepo {
  private final Map<Long,Booking> store = new ConcurrentHashMap<>();
  public void save(Booking b){
    store.put(b.getId(), b);
  }
  public Optional<Booking> findById(Long id){
    return Optional.ofNullable(store.get(id));
  }
  public List<Booking> findAll(){
    return new ArrayList<>(store.values());
  }
}
