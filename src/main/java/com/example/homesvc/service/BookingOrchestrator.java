package com.example.homesvc.service;
import com.example.homesvc.af.ComponentsFactory;
import com.example.homesvc.domain.*; import com.example.homesvc.dto.*; import com.example.homesvc.repo.*; import org.springframework.stereotype.Service;
import java.math.BigDecimal; import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional; import java.util.concurrent.atomic.AtomicLong;
@Service
public class BookingOrchestrator {
  private final UserRepo users;
  private final ProviderRepo providers;
  private final BookingRepo bookings;
  private final PricingService pricing;
  private final ProviderMatchingService matching;
  private final PaymentService payments;
  private final NotificationService notify;
  private final AtomicLong seq = new AtomicLong(1000);
  private final BookingStateMachine stateMachine;
  private final ComponentsFactory factory;
  public BookingOrchestrator(UserRepo users, ProviderRepo providers, BookingRepo bookings, PricingService pricing,
                             ProviderMatchingService matching, PaymentService payments,
                             NotificationService notify, BookingStateMachine stateMachine,
                             ComponentsFactory factory){
    this.users=users;
    this.providers=providers;
    this.bookings=bookings;
    this.pricing=pricing;
    this.matching=matching;
    this.payments=payments;
    this.notify=notify;
    this.stateMachine = stateMachine;
    this.factory = factory;
  }
  public QuoteResponse quote(QuoteRequest req){
    var user = users.findById(req.userId)
            .orElseThrow();
    UserTier tier = req.tierOverride!=null? req.tierOverride : user.getTier();
    var calc = pricing.estimate(
            req.region,
            req.serviceType,
            req.urgent, tier,
            req.voucherCode,
            req.desiredAt);

    var match = matching.suggest(
            req.region,
            req.serviceType,
            req.extra);

    return new QuoteResponse(
            calc.estimate(),
            calc.taxes(),
            calc.surcharges(),
            calc.notes(),
            match.providerIds(),
            match.notes(),
            LocalDateTime.now());
  }
  public BookingView create(CreateBookingRequest bookReq){
    var user = users.findById(bookReq.userId).orElseThrow();

    var calc = pricing
            .estimate(bookReq.region, bookReq.serviceType, bookReq.highPriority, user.getTier(),
            bookReq.voucherCode, bookReq.scheduledAt);

    BigDecimal amount = calc.estimate();

    Long providerId = bookReq.preferredProviderId;

    if(providerId == null){
      var match = matching.suggest(bookReq.region,
              bookReq.serviceType,
              Map.of("algo","BALANCED"));

      providerId = match.providerIds().isEmpty() ? null : match.providerIds().get(0);
    }
    if (providerId == null){
      throw new IllegalStateException("No providers available");
    }
    //var payOld = payments.charge(bookReq.paymentMethod, amount);
    Result pay = factory.paymentGateway().capture(amount, factory.currency(),
            user.getId());

    //BookingStatus status =
        //    pay.success()? BookingStatus.CONFIRMED : BookingStatus.FAILED_PAYMENT;
    //NEW: Always start as QUOTED; state machine decides the next status.
    var booking = new Booking(seq.incrementAndGet(),
            user.getId(),
            providerId,
            bookReq.serviceType,
            bookReq.region,
            bookReq.scheduledAt,
            BookingStatus.QUOTED,//status,
            amount,
            null,//pay.success()? amount : null,
            "init");//pay.code());
    //bookings.save(booking);
//NEW: Transition based on payment result
    if(pay.success()){
      stateMachine.apply(booking, BookingEvent.PAYMENT_AUTHORIZED);// confirmed
      booking.setFinalPrice(amount);
      booking.setNotes(pay.code());

      notify.sendBookingConfirmation(user.getId(), booking.getId(), providerId);
    } /*New*/ else{
      stateMachine.apply(booking, BookingEvent.PAYMENT_FAILED);
      booking.setNotes(pay.code());
    }

    bookings.save(booking);

    var v = new BookingView();
    v.id=booking.getId();
    v.userId=booking.getUserId();
    v.providerId=booking.getProviderId();
    v.serviceType=booking.getServiceType();
    v.region=booking.getRegion();
    v.scheduledAt=booking.getScheduledAt();
    v.status=booking.getStatus();
    v.quotedPrice=booking.getQuotedPrice();
    v.finalPrice=booking.getFinalPrice();
    v.notes=booking.getNotes();
    return v;
  }
  public Optional<BookingView> get(Long id){
    return bookings.findById(id)
            .map(b -> {
              var v = new BookingView();
              v.id=b.getId();
              v.userId=b.getUserId();
              v.providerId=b.getProviderId();
              v.serviceType=b.getServiceType();
              v.region=b.getRegion();
      v.scheduledAt=b.getScheduledAt();
      v.status=b.getStatus();
      v.quotedPrice=b.getQuotedPrice();
      v.finalPrice=b.getFinalPrice();
      v.notes=b.getNotes();
      return v;});
  }
  public BookingView start(Long id) {
    var b = bookings.findById(id)
            .orElseThrow();
    stateMachine.apply(b, BookingEvent.START_WORK);
    bookings.save(b);
    return toView(b);
  }
  public BookingView complete(Long id){
    var b = bookings.findById(id).orElseThrow();
    stateMachine.apply(b, BookingEvent.COMPLETE_WORK); // IN_PROGRESS -> COMPLETED
    bookings.save(b);
    return toView(b);
  }

  public BookingView cancel(Long id){
    var b = bookings.findById(id).orElseThrow();
    stateMachine.apply(b, BookingEvent.CANCEL); // Many states -> CANCELLED (if allowed)
    bookings.save(b);
    return toView(b);
  }
  private BookingView toView(Booking b){
    var v = new BookingView();
    v.id = b.getId();
    v.userId = b.getUserId();
    v.providerId = b.getProviderId();
    v.serviceType = b.getServiceType(); v.region = b.getRegion();
    v.scheduledAt = b.getScheduledAt(); v.status = b.getStatus();
    v.quotedPrice = b.getQuotedPrice(); v.finalPrice = b.getFinalPrice(); v.notes = b.getNotes();
    return v;
  }


}
