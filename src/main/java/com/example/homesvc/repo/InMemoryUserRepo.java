package com.example.homesvc.repo;
import com.example.homesvc.domain.User;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class InMemoryUserRepo implements UserRepo {
  private final Map<Long,User> store = new ConcurrentHashMap<>();
  public Optional<User> findById(Long id){ return java.util.Optional.ofNullable(store.get(id)); }
  public void save(User u){ store.put(u.getId(), u); }
  public List<User> findAll(){ return new java.util.ArrayList<>(store.values()); }
}
