package com.example.homesvc.domain;
import java.math.BigDecimal; import java.util.EnumSet; import java.util.Set;
public class Provider {
  private Long id;
  private String name;
  private Region region;
  private Set<ServiceType> skills = EnumSet.noneOf(ServiceType.class);
  private BigDecimal hourlyRate;
  private boolean licensed;
  private int reputation;
  public Provider() {}
  public Provider(Long id, String name, Region region, Set<ServiceType> skills, BigDecimal hourlyRate, boolean licensed, int reputation){
    this.id=id;
    this.name=name;
    this.region=region; this.skills=skills; this.hourlyRate=hourlyRate; this.licensed=licensed; this.reputation=reputation;
  }
  public Long getId(){ return id; } public String getName(){ return name; } public Region getRegion(){ return region; } public Set<ServiceType> getSkills(){ return skills; }
  public java.math.BigDecimal getHourlyRate(){ return hourlyRate; } public boolean isLicensed(){ return licensed; } public int getReputation(){ return reputation; }
  public void setId(Long id){ this.id=id; } public void setName(String name){ this.name=name; } public void setRegion(Region region){ this.region=region; } public void setSkills(Set<ServiceType> s){ this.skills=s; }
  public void setHourlyRate(java.math.BigDecimal r){ this.hourlyRate=r; } public void setLicensed(boolean l){ this.licensed=l; } public void setReputation(int r){ this.reputation=r; }
}
