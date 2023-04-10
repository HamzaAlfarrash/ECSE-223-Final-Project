package ca.mcgill.ecse.snowshoetours.controller;

public class TOGuide{
  private String guideAccountName;
  
  public TOGuide(String email) {
    this.guideAccountName = email;
  }
  
  public void setGuideAccountName(String email) {
    this.guideAccountName = email;
  }
  
  public String getGuideAccountName() {
    return guideAccountName;
  }
  
  public String toString(){
    return guideAccountName;
  }
}