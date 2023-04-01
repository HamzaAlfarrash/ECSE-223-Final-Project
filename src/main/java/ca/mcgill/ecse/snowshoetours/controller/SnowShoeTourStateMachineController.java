package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
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
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    List<Guide> guides = sst.getGuides();
    int id = 0; //will be used for id of a tour
    int i = 0;
    //for each participant, assign a Tour to it with matching startweek and endweek
    for(Participant par : list) {
      int nOfWeeks = par.getNrWeeks();
      int weekAvailableFrom = par.getWeekAvailableFrom();
      id++;
      Tour tour = sst.addTour(id,weekAvailableFrom,weekAvailableFrom + nOfWeeks -1,sst.getGuide(i%guides.size())); //assign randomly a guide to the tour, starting from index 0 in the guide list
      par.assign(tour); //assign tour to participant, state is not "Assigned"
      i++;
    }
    return "";
  }
  
  
  public static String finishTour() {
    return "";
  }
  
  public static String startTourForWeek(int Week) {
    return "";
  }
  
  public static String confirmPayement(String email, String authorizationCode) {
    return "";
  }
}

