package com.example.homesvc.domain;
public class User {
  private Long id; private String name; private UserTier tier; private Region region;
  public User() {}
  public User(Long id, String name, UserTier tier, Region region){ this.id=id; this.name=name; this.tier=tier; this.region=region; }
  public Long getId(){
    return id;
  }
  public String getName(){
    return name;
  }
  public UserTier getTier(){
    return tier;
  }
  public Region getRegion(){
    return region;
  }
  public void setId(Long id){
    this.id=id;
  }
  public void setName(String name){
    this.name=name;
  }
  public void setTier(UserTier tier){
    this.tier=tier;
  }
  public void setRegion(Region region){
    this.region=region;
  }
}
