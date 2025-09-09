package com.example.homesvc.domain;
import java.math.BigDecimal; import java.time.LocalDateTime;
public class Booking {
  private Long id;
  private Long userId;
  private Long providerId;
  private ServiceType serviceType;
  private Region region;
  private LocalDateTime scheduledAt;
  private BookingStatus status;
  private BigDecimal quotedPrice;
  private BigDecimal finalPrice;
  private String notes;
  public Booking() {}
  public Booking(Long id, Long userId, Long providerId, ServiceType st, Region region, LocalDateTime at, BookingStatus status, BigDecimal quoted, BigDecimal fin, String notes){
    this.id=id;
    this.userId=userId;
    this.providerId=providerId;
    this.serviceType=st;
    this.region=region;
    this.scheduledAt=at;
    this.status=status;
    this.quotedPrice=quoted;
    this.finalPrice=fin;
    this.notes=notes;
  }
  public Long getId(){
    return id;
  }
  public Long getUserId(){
    return userId;
  }
  public Long getProviderId(){
    return providerId;
  }
  public ServiceType getServiceType(){
    return serviceType;
  }
  public Region getRegion(){ return region; }
  public LocalDateTime getScheduledAt(){ return scheduledAt; }
  public BookingStatus getStatus(){ return status; }
  public BigDecimal getQuotedPrice(){ return quotedPrice; }
  public java.math.BigDecimal getFinalPrice(){ return finalPrice; }
  public String getNotes(){ return notes; }
  public void setId(Long id){ this.id=id; }
  public void setUserId(Long idu){ this.userId=idu; }
  public void setProviderId(Long idp){ this.providerId=idp; } public void setServiceType(ServiceType s){ this.serviceType=s; }
  public void setRegion(Region r){ this.region=r; }
  public void setScheduledAt(LocalDateTime t){ this.scheduledAt=t; }
  public void setStatus(BookingStatus s){ this.status=s; }
  public void setQuotedPrice(java.math.BigDecimal q){ this.quotedPrice=q; }
  public void setFinalPrice(java.math.BigDecimal f){ this.finalPrice=f; }
  public void setNotes(String n){ this.notes=n; }
}
