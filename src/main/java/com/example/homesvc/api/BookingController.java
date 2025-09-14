package com.example.homesvc.api;

import com.example.homesvc.dto.BookingView;
import com.example.homesvc.dto.CreateBookingRequest;
import com.example.homesvc.dto.QuoteRequest;
import com.example.homesvc.dto.QuoteResponse;
import com.example.homesvc.service.BookingOrchestrator;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  private final BookingOrchestrator orchestrator;
  public BookingController(BookingOrchestrator orchestrator){
    this.orchestrator=orchestrator;
  }
  @PostMapping("/quote")
  public QuoteResponse quote(@Valid @RequestBody QuoteRequest req){
    return orchestrator
            .quote(req);
  }
  @PostMapping
  public BookingView create(@Valid @RequestBody CreateBookingRequest req){
    return orchestrator
            .create(req);
  }
  @GetMapping("/{id}")
  public Optional<BookingView> get(@PathVariable Long id){

    return orchestrator.get(id);
  }
  @PostMapping("/{id}/start")
  public BookingView start(@PathVariable Long id){
    return orchestrator.start(id);
  }

  @PostMapping("/{id}/complete")
  public BookingView complete(@PathVariable Long id){
    return orchestrator.complete(id);
  }

  @PostMapping("/{id}/cancel")
  public BookingView cancel(@PathVariable Long id){
    return orchestrator.cancel(id);
  }
}
