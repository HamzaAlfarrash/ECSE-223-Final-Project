package ca.mcgill.ecse.snowshoetours.controller;

public class TOParticipant{
  private String participantAccountName;
  
  public TOParticipant(String email) {
    this.participantAccountName = email;
  }
  
  public void setParticipantAccountName(String email) {
    this.participantAccountName = email;
  }
  
  public String getParticipantAccountName() {
    return participantAccountName;
  }
  
}