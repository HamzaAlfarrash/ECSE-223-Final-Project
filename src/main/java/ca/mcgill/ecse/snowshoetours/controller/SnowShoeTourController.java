package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Tour;
import ca.mcgill.ecse.snowshoetours.persistence.SstPersistence;

public class SnowShoeTourController {

  /**
   * @author souhail el hayani
   * @return list of transfer objects of tours
   */
  public static List<TOSnowShoeTour> getSnowShoeTours() {
    List<TOSnowShoeTour> tours = new ArrayList<>();
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    for(Tour tour : sst.getTours()) {
      tours.add(getSnowShoeTour(tour.getId()));
    }
    return tours;
  }
  
  /**
   * @author Hamza Alfarrash returns transfer object SnowShoeTour
   * @param id
   * @return TOSnowShoeTour
   */
  public static TOSnowShoeTour getSnowShoeTour(int id) {
    // TODO Implement the method
    // Checking if the tour exist in the system
    if (!(Tour.hasWithId(id))) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return null;
      }
      return null;
    }
    Tour tour = Tour.getWithId(id);
    // Getting parameters for TOSnowShoeTourCreation:

    // StartWeek
    int startWeek = tour.getStartWeek();

    // EndWeek
    int endWeek = tour.getEndWeek();

    // GuideEmail
    String guideEmail = tour.getGuide().getAccountName();

    // GuideName
    String guideName = tour.getGuide().getName();

    // Total cost for guide [Unsure]
    int totalCostForGuide = tour.getSnowShoeTour().getPriceOfGuidePerWeek()
        * (tour.getEndWeek() - tour.getStartWeek() + 1); // * tour.getSnowShoeTour().getNrWeeks();

    // ^^^^------------------------------------------------------^^^^

    // defining the variable error
    String error = "";

    try {

      String participantEmail; // defining a variable of type String for the participant email
      String participantName; // defining a variable of type String for the participant name
      int refundedPercentageAmount; // defining a variable of type String for the participant
                                    // refunded percentage amount


      // Creating an array of TOParticipantCost
      TOParticipantCost[] allParticipantCosts =
          new TOParticipantCost[tour.getParticipants().size()];

      // Defining variable i to keep track of the for loop
      int i = 0;

      // For loop that will iterate through the participants
      for (Participant p : tour.getParticipants()) {

        // get the participant name of the snow tour with specific id
        participantName = p.getName();

        // get the participant email of the snow tour with specific id
        participantEmail = p.getAccountName();

        // get the participant refunded percentage
        refundedPercentageAmount = p.getRefundedPercentageAmount();

        // Check if the participant requested a lodge
        boolean lodgeRequired = p.getLodgeRequired();

        // creating a variable to keep track of the total cost for bookable items per participant
        int totalCostForBookableItems = 0;

        // creating a variable for the total cost of the Snow tour per participant
        int totalCostForSnowTour = 0;

        // A for loop to iterate through the booked item of each participant (inside the big for
        // loop) to compute the cost
        for (BookedItem item : p.getBookedItems()) {

          // gets the item
          BookableItem BI = item.getItem();

          // If it is a gear, it computes the following formula for cost
          if (BI instanceof Gear) {
            totalCostForBookableItems +=
                ((Gear) BI).getPricePerWeek() * (endWeek - startWeek + 1) * item.getQuantity(); // +1
          }

          // If it is a combo, it gets the combo items and discount
          if (BI instanceof Combo) {
            Combo combo = (Combo) BI;
            int discount = lodgeRequired ? combo.getDiscount() : 0;
            List<ComboItem> comboItems = combo.getComboItems();
            double comboPricePerWeek = 0;

            // It also gets the quantity of each item in the combo (the for loop iterates through
            // the combo items)
            for (ComboItem g : comboItems) {
              int quantity = g.getQuantity();

              // Computes the Combo Price per week before the discount
              comboPricePerWeek += quantity * g.getGear().getPricePerWeek();
            }

            // Computes the combo price per week after the discount
            comboPricePerWeek = comboPricePerWeek * (1 - discount * 0.01);

            // Computes the total cost for bookable items (for the total number of weeks)
            totalCostForBookableItems +=
                comboPricePerWeek * (endWeek - startWeek + 1) * item.getQuantity(); // +1
          }

        }

        // get the total costs for snow tour per participant of the snow tour with specific id with
        // this formula:
        totalCostForSnowTour = totalCostForBookableItems + totalCostForGuide;

        // Creates a new TOParticipantCost with the parameters that we got previously
        TOParticipantCost top = new TOParticipantCost(participantEmail, participantName,
            totalCostForBookableItems, totalCostForSnowTour);

        // Each time we're going through a participant and getting the respective information, we're
        // adding it to the list allParticipantCosts
        allParticipantCosts[i] = top;
        i++;

      }


      // We create a new object of type TOSnowShoeTour with the respective parameters we got
      // previously
      TOSnowShoeTour TheSnowShoeTour = new TOSnowShoeTour(id, startWeek, endWeek, guideEmail,
          guideName, totalCostForGuide, allParticipantCosts);

      // return the TOSnowShoeTour TheSnowShoeTour
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return null;
      }
      return TheSnowShoeTour;

    }

    catch (RuntimeException e) {
      error = e.getMessage(); // will catch exceptions errors and get the respective error message
    }

    if (!error.isEmpty()) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return null;
      }
      return null; // We don't have errors to return
    }
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return null;
    }
    return null;
  }
  
  public static TOSnowShoeTourSeason getSnowShoeTourSeason() {
	  SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
	  TOSnowShoeTourSeason ssts = new TOSnowShoeTourSeason(sst.getStartDate(),sst.getNrWeeks(),sst.getPriceOfGuidePerWeek());
	  try {
	        SstPersistence.save();
	      } catch (RuntimeException e) {
	  return null;
	  }
	  return ssts;
  }

  /**
   * @author Souhail El Hayani updates SnowShoeTour
   * @param startDate
   * @param nrWeeks
   * @param priceOfGuidePerWeek
   * @return error message
   */
  public static String updateSnowShoeTour(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
    // TODO Implement the method, return error message (if any)
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

    // input validation
    if (nrWeeks < 0) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return null;
      }
      return "The number of riding weeks must be greater than or equal to zero"; // if an invalid
    }
    // input for number
    // of weeks is
    // inputted
    if (priceOfGuidePerWeek < 0) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return null;
      }
      return "The price of guide per week must be greater than or equal to zero"; // identical check
    }
    // for the price
    // per week
    if (startDate.getYear() < sst.getStartDate().getYear()) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return null;
      }
      return "The start date cannot be from previous year or earlier"; // if the start date is from
    }
    // the previous year - which
    // cannot occur

    sst.setStartDate(startDate); // sets start date
    sst.setNrWeeks(nrWeeks); // sets number of weeks
    sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek); // sets price of guide per week
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return null;
    }
    return "";
  }
}
