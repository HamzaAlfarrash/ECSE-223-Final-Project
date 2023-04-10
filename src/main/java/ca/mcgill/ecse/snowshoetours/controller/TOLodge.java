package ca.mcgill.ecse.snowshoetours.controller;

import ca.mcgill.ecse.snowshoetours.model.Lodge.LodgeRating;

public class TOLodge{
  private String lodgeName;
  private String lodgeAddress;
  LodgeRating numberOfStars;
  
  public TOLodge(String lodge, String address, LodgeRating lodgeRating) {
    this.lodgeName = lodge;
    this.lodgeAddress = address;
    this.numberOfStars = lodgeRating;
  }
  
  public void setLodgeName(String name) {
    this.lodgeName = name;
  }
  
  public void setLodgeNumberOfStars(LodgeRating stars){
    this.numberOfStars = stars;
  }
  
  public void setLodgeAddress(String address) {
    this.lodgeName = address;
  }
  
  public String getLodgeName() {
    return lodgeName;
  }
  
  public LodgeRating getNumberOfStars() {
    return numberOfStars;
  }
  
  public String getLodgeAddress() {
    return lodgeAddress;
  }
  
}