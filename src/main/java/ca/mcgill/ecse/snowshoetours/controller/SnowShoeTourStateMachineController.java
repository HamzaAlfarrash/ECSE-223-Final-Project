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
    if(selected.getStatus()==Status.Finished) return "Cannot cancel tour because the participant has finished their tour";
    if(selected.getStatus()==Status.Cancelled) return "Cannot cancel tour because the participant has already cancelled their tour";
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
    //for each participant, assign a Tour to it with matching startweek and endweek
    for(Participant par : list) {
      int nOfWeeks = par.getNrWeeks();
      int weekAvailableFrom = par.getWeekAvailableFrom();
      int i = id; //index to get the guide
      id++;
      Tour tour = sst.addTour(id,weekAvailableFrom,weekAvailableFrom + nOfWeeks -1,sst.getGuide(i%guides.size())); //assign randomly a guide to the tour, starting from index 0 in the guide list
      par.assign(tour); //assign tour to participant, state is not "Assigned"
    }
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
      if (participant.getAccountName().equals(email)) {
        finishedParticipant = participant;
        break;
      }
    }
    if (finishedParticipant == null) return "<error>"; 
    if (!(finishedParticipant.getStatus() == Status.Finished)) {
      return "This participant's trip is not finished.";
    }
    if (finishedParticipant.getStatus() == Status.Finished) {
      finishedParticipant.finishTrip(); 
    }
    return "";
  }
  
  /**
   * @author Yassine Mimet
   * @param week
   * @return
   */
  public static String startTourForWeek(int week) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Tour> tours = sst.getTours();
    String error = "";
    for(Tour tour : tours) {
     if (tour.getStartWeek() == week) {
       List<Participant> participants = tour.getParticipants();
       for (Participant participant : participants) {
         if( participant.getStatus().equals(Status.Started)) error = "Cannot start tour because the participant has already started their tour";
         else if (participant.getStatus().equals(Status.Cancelled)) error = "Cannot start tour because the participant has cancelled their tour";
         else if (participant.getStatus().equals(Status.Finished)) error = "Cannot start tour because the participant has finished their tour";
         else participant.startTrip(week);
       }
     }
   }
   return error;
  }
  /**
   * @author Souhail El Hayani
   * @param email
   * @param authorizationCode
   * @return
   */
  public static String confirmPayement(String email, String authorizationCode) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    if(authorizationCode == null || authorizationCode.isBlank()) {
      return "Invalid authorization code";
    }
    Participant aParticipant = null; 
    List<Participant> participants = sst.getParticipants();
    for(Participant participant : participants) {
      if(participant.getAccountName().equals(email)) {
        aParticipant = participant;
        break;
      }
    }
    if(aParticipant == null) return "Participant with email address " + email + " does not exist";
    else if (aParticipant.getStatus().equals(Status.NotAssigned)) return "The participant has not been assigned to their tour";
    else if (aParticipant.getStatus().equals(Status.Paid)) return "The participant has already paid for their tour";
    else if (aParticipant.getStatus().equals(Status.Started)) return "The participant has already paid for their tour";
    else if (aParticipant.getStatus().equals(Status.Finished)) return "The participant has already paid for their tour";
    else if (aParticipant.getStatus().equals(Status.Cancelled)) return "Cannot pay for tour because the participant has cancelled their tour";
    else aParticipant.pay(authorizationCode);
    return "";
  }
}

