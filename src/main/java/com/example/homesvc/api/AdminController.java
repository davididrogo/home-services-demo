package com.example.homesvc.api;
import com.example.homesvc.domain.*;
import com.example.homesvc.repo.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping("/api/admin")
public class AdminController {
  private final UserRepo users; private final ProviderRepo providers; private final BookingRepo bookings;
  public AdminController(UserRepo users, ProviderRepo providers, BookingRepo bookings){ this.users=users; this.providers=providers; this.bookings=bookings; }
  @PostMapping("/seed")
  public String seed(){
    var uid = new AtomicLong(1);
    users.save(new User(uid.get(), "Alice", UserTier.REGULAR, Region.NORTH));
    users.save(new User(uid.incrementAndGet(), "Bob", UserTier.GOLD, Region.SOUTH));
    users.save(new User(uid.incrementAndGet(), "Carol", UserTier.PLATINUM, Region.EAST));
    providers.save(new Provider(10L, "PlumbMaster", Region.NORTH, java.util.EnumSet.of(ServiceType.PLUMBING), new BigDecimal("75"), true, 80));
    providers.save(new Provider(11L, "SparkPro", Region.NORTH, java.util.EnumSet.of(ServiceType.ELECTRICAL), new BigDecimal("85"), true, 70));
    providers.save(new Provider(12L, "HVACo", Region.EAST, java.util.EnumSet.of(ServiceType.HVAC), new BigDecimal("95"), true, 90));
    providers.save(new Provider(13L, "FixItAll", Region.SOUTH, java.util.EnumSet.of(ServiceType.PLUMBING, ServiceType.APPLIANCE), new BigDecimal("60"), false, 60));
    providers.save(new Provider(14L, "ApplianceGurus", Region.WEST, java.util.EnumSet.of(ServiceType.APPLIANCE), new BigDecimal("65"), true, 75));
    providers.save(new Provider(15L, "WireWizards", Region.NORTH, java.util.EnumSet.of(ServiceType.ELECTRICAL), new BigDecimal("78"), true, 88));
    return "seeded";
  }
  @GetMapping("/users")
  public List<User> users(){
    return users.findAll(); }
  @GetMapping("/providers")
  public List<Provider> providers(){
    return providers.findAll(); }
  @GetMapping("/bookings")
  public List<Booking> bookings(){
    return bookings.findAll(); }
}
