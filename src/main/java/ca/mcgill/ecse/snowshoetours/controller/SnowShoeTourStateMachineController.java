package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.Participant.Status;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Tour; 

public class SnowShoeTourStateMachineController {
  
  /**
   * @author souhail el hayani
   * @param name
   * @return error message if any
   */
  public static String cancelTrip(String name) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    Participant selected = null;
    for(Participant par : list) {
      if(par.getAccountName().equals(name)) {
        selected = par;  
        break;
      }
    }
    if(selected == null) return "<error>";
    if(selected.getStatus()==Status.FinishedParticipant) return "Cannot cancel tour because the participant has finished their tour";
    if(selected.getStatus()==Status.CanceledParticipant) return "Cannot cancel tour because the participant has already cancelled their tour";
    selected.cancel();
    return "";
  }
  
  /**
   * @author souhail el hayani
   * @return
   */
  public static String initiate() {
    return "";
  }
  
  /**
   * @author Wasif Somji
   * @param email
   * @return error message if any
   */
  public static String finishTour(String email) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    Participant finishedParticipant = null; 
    for (Participant participant : list) {
      if (participant.getAccountName().equals(emai)) {
        finishedParticipant = participant;
        break;
      }
    }
    if (finishedParticipant == null) return "<error>"; 
    if (!(finishedParticipant.getStatus == Status.FinishedParticipant)) {
      return "This participant's trip is not finished."
    }
    if (finishedParticipant.getStatus == Status.FinishedParticipant) {
      finishedParticipant.finishTrip(); 
    }
    return "";
  }
  
  public static String startTourForWeek(int Week) {
    return "";
  }
  
  public static String confirmPayement(String email, String authorizationCode) {
    return "";
  }
}

