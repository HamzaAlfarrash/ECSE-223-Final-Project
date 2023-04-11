package ca.mcgill.ecse.snowshoetours.controller;

public class TOParticipant{
  private String participantAccountName;
  private String authorizationCode;

  public TOParticipant(String email, String code) {
    this.participantAccountName = email;
    this.authorizationCode = code;
  }

  public void setParticipantAccountName(String email) {
    this.participantAccountName = email;
  }

  public String getParticipantAccountName() {
    return participantAccountName;
  }

  public String getAuthorizationCode() {
    return authorizationCode;
  }

  public String toString(){
    return participantAccountName;
  }
}
