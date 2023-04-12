package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.Participant.Status;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Tour;
import ca.mcgill.ecse.snowshoetours.persistence.SstPersistence;

public class SnowShoeTourStateMachineController {

  /**
   * @author souhail el hayani
   * @param name
   * @return error message if any
   */
  public static String cancelTrip(String name) {
    String error = "";
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); //this selection of getting participants will be used throughout, will not be repeating this documentation to reduce redundancy
    List<Participant> list = sst.getParticipants(); // assigns the list to the participants of the SnowSheTour
    Participant selected = null; //initializes participant
    for (Participant par : list) { //iterates through the list
      if (par.getAccountName().equals(name)) { // finds the matching participant
        selected = par; // assigns if it matches
        break;
      }
    }
    if (selected == null) { //edge cases
      error = "Participant with email address " + name + " does not exist"; // if the participant's email address doesn't exist
    } else if (selected.getStatus() == Status.Finished) { // if their tour is already finished
      error = "Cannot cancel tour because the participant has finished their tour";
    } else if (selected.getStatus() == Status.Cancelled) { // if their tour has already been cancelled
      error = "Cannot cancel tour because the participant has already cancelled their tour";
    } else
      selected.cancel(); //cancels the selected participant's trip
    try {
      SstPersistence.save(); // for persistence; same try catch loop throughout; saves changes and throws errors if need be, will not be repeating this documentation to reduce redundancy
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return error;
  }

  /**
   * @author souhail el hayani
   * @return
   */
  public static String initiate() {
	  System.out.println("Initializing attempt");
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    List<Guide> guides = sst.getGuides();
    //getting the current max id for a tour, so no duplicates are created
    List<Tour> allTours = sst.getTours();
    int maxId = 1;
    for (var t:allTours) {
    	if(t.getId() > maxId) {
    		maxId = t.getId();
    	}
    }
    System.out.println(maxId);
    int id = maxId; // will be used for id of a tour
    // for each participant, assign a Tour to it with matching startweek and endweek
    for (Participant par : list) {
      if(par.getStatus()!=Status.NotAssigned) continue;
      int nOfWeeks = par.getNrWeeks(); // gets number of weeks
      int weekAvailableFrom = par.getWeekAvailableFrom(); // gets start week
      int i = id; // index to get the guide
      id++;
      Tour tour = sst.addTour(id, weekAvailableFrom, weekAvailableFrom + nOfWeeks - 1,
          sst.getGuide(i % guides.size())); // assign randomly a guide to the tour, starting from
                                            // index 0 in the guide list
      par.assign(tour); // assign tour to participant, state is not "Assigned"
    }
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    System.out.println("Initializing success");
    return "";
  }

  /**
   * @author Wasif Somji
   * @param email
   * @return error message if any
   */
  public static String finishTour(String email) {
    String error = "";
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    Participant finishedParticipant = null;
    for (Participant participant : list) {
      if (participant.getAccountName().equals(email)) {
        finishedParticipant = participant; // 
        break;
      }
    }
    if (finishedParticipant == null)
      error = "Participant with email address nonexisting@mail.ca does not exist"; // if the email doesn't exist
    else if (finishedParticipant.getStatus() == Status.NotAssigned)
      error = "Cannot finish a tour for a participant who has not started their tour"; // if the participant has not started the tour (not assigned)
    else if (finishedParticipant.getStatus() == Status.Assigned)
      error = "Cannot finish a tour for a participant who has not started their tour"; // if the participant has not started the tour (assigned)
    else if (finishedParticipant.getStatus() == Status.Paid)
      error = "Cannot finish a tour for a participant who has not started their tour"; // if the participant has not started the tour (paid)
    else if (finishedParticipant.getStatus() == Status.Cancelled)
      error = "Cannot finish tour because the participant has cancelled their tour"; // if the participant has already cancelled the tour
    else if (finishedParticipant.getStatus() == Status.Finished)
      error = "Cannot finish tour because the participant has already finished their tour"; // if the participant's tour status is ALREADY finished
    else
      finishedParticipant.finishTrip();
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return error; //returns error if there is one; returns nothing if there isn't
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
    for (Tour tour : tours) {
      if (tour.getStartWeek() == week) {
        List<Participant> participants = tour.getParticipants();
        for (Participant participant : participants) { // iterates through the participants list
          if (participant.getStatus().equals(Status.Started))
            error = "Cannot start tour because the participant has already started their tour"; // if the participant's tour has already started
          else if (participant.getStatus().equals(Status.Cancelled))
            error = "Cannot start tour because the participant has cancelled their tour"; // if the participant's tour has been cancelled
          else if (participant.getStatus().equals(Status.Finished))
            error = "Cannot start tour because the participant has finished their tour"; // if the participant's tour is finished
          else
            participant.startTrip(week);
        }
      }
    }
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return error; //returns error if there is one; returns nothing if there isn't
  }

  /**
   * @author Souhail El Hayani
   * @param email
   * @param authorizationCode
   * @return
   */
  public static String confirmPayement(String email, String authorizationCode) {
    String error = "";
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    if (authorizationCode == null || authorizationCode.isBlank()) { // if the authorization code is null or blank
      return "Invalid authorization code";
    }
    Participant aParticipant = null;
    List<Participant> participants = sst.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(email)) {
        aParticipant = participant;
        break;
      }
    }
    if (aParticipant == null)
      error = "Participant with email address " + email + " does not exist"; // if the participant doesn't exist
    else if (aParticipant.getStatus().equals(Status.NotAssigned))
      error = "The participant has not been assigned to their tour"; // if the participant has not been assigned to a tour
    else if (aParticipant.getStatus().equals(Status.Paid))
      error = "The participant has already paid for their tour"; // if the participant has already paid
    else if (aParticipant.getStatus().equals(Status.Started))
      error = "The participant has already paid for their tour"; // same error as above
    else if (aParticipant.getStatus().equals(Status.Finished))
      error = "The participant has already paid for their tour"; // same error as above
    else if (aParticipant.getStatus().equals(Status.Cancelled))
      error = "Cannot pay for tour because the participant has cancelled their tour"; // if the participant has already cancelled their tour
    else
      aParticipant.pay(authorizationCode);

    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return error; //returns error if there is one; returns nothing if there isn't

  }
}

