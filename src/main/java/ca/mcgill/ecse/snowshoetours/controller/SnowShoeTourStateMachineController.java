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
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    Participant selected = null;
    for (Participant par : list) {
      if (par.getAccountName().equals(name)) {
        selected = par;
        break;
      }
    }
    if (selected == null) {
      error = "Participant with email address " + name + " does not exist";
    } else if (selected.getStatus() == Status.Finished) {
      error = "Cannot cancel tour because the participant has finished their tour";
    } else if (selected.getStatus() == Status.Cancelled) {
      error = "Cannot cancel tour because the participant has already cancelled their tour";
    } else
      selected.cancel();
    try {
      SstPersistence.save();
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
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Participant> list = sst.getParticipants();
    List<Guide> guides = sst.getGuides();
    int id = 0; // will be used for id of a tour
    // for each participant, assign a Tour to it with matching startweek and endweek
    for (Participant par : list) {
      int nOfWeeks = par.getNrWeeks();
      int weekAvailableFrom = par.getWeekAvailableFrom();
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
        finishedParticipant = participant;
        break;
      }
    }
    if (finishedParticipant == null)
      error = "Participant with email address nonexisting@mail.ca does not exist";
    else if (finishedParticipant.getStatus() == Status.NotAssigned)
      error = "Cannot finish a tour for a participant who has not started their tour";
    else if (finishedParticipant.getStatus() == Status.Assigned)
      error = "Cannot finish a tour for a participant who has not started their tour";
    else if (finishedParticipant.getStatus() == Status.Paid)
      error = "Cannot finish a tour for a participant who has not started their tour";
    else if (finishedParticipant.getStatus() == Status.Cancelled)
      error = "Cannot finish tour because the participant has cancelled their tour";
    else if (finishedParticipant.getStatus() == Status.Finished)
      error = "Cannot finish tour because the participant has already finished their tour";
    else
      finishedParticipant.finishTrip();
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return error;
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
        for (Participant participant : participants) {
          if (participant.getStatus().equals(Status.Started))
            error = "Cannot start tour because the participant has already started their tour";
          else if (participant.getStatus().equals(Status.Cancelled))
            error = "Cannot start tour because the participant has cancelled their tour";
          else if (participant.getStatus().equals(Status.Finished))
            error = "Cannot start tour because the participant has finished their tour";
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
    return error;
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
    if (authorizationCode == null || authorizationCode.isBlank()) {
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
      error = "Participant with email address " + email + " does not exist";
    else if (aParticipant.getStatus().equals(Status.NotAssigned))
      error = "The participant has not been assigned to their tour";
    else if (aParticipant.getStatus().equals(Status.Paid))
      error = "The participant has already paid for their tour";
    else if (aParticipant.getStatus().equals(Status.Started))
      error = "The participant has already paid for their tour";
    else if (aParticipant.getStatus().equals(Status.Finished))
      error = "The participant has already paid for their tour";
    else if (aParticipant.getStatus().equals(Status.Cancelled))
      error = "Cannot pay for tour because the participant has cancelled their tour";
    else
      aParticipant.pay(authorizationCode);

    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
    return error;

  }
}

