package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;
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

public class SnowShoeTourController {
	
	/**
	 * @author Hamza Alfarrash
	 * returns transfer object SnowShoeTour
	 * @param id
	 * @return TOSnowShoeTour
	 */
  public static TOSnowShoeTour getSnowShoeTour(int id) {
    // TODO Implement the method
	  //Checking if the tour exist in the system
	  if (!(Tour.hasWithId(id))){
		  return null;
	  }
	  Tour tour = Tour.getWithId(id);
	  //Getting parameters for TOSnowShoeTourCreation:
	  
	  //StartWeek
	  int startWeek = tour.getStartWeek();
	  
	  //EndWeek
	  int endWeek = tour.getEndWeek();
	  
	  //GuideEmail
	  String guideEmail = tour.getGuide().getAccountName();
	  
	  //GuideName
	  String guideName = tour.getGuide().getName();
	  
	  //Total cost for guide [Unsure]
	  int totalCostForGuide = tour.getSnowShoeTour().getPriceOfGuidePerWeek() * tour.getSnowShoeTour().getNrWeeks();
	  
	  //^^^^------------------------------------------------------^^^^

	    // defining the variable error
	  String error = "";

	    try {

	      String participantEmail; // defining a variable of type String for the participant email
	      String participantName; // defining a variable of type String for the participant name
	      String authorizationCode; // defining a variable of type String for the participant authorization code
	      String participantStatus; // defining a variable of type String for the participant status
	      int refundedPercentageAmount; // defining a variable of type String for the participant refunded percentage amount


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

	        // get the participant authorization code
	        authorizationCode = p.getAuthorizationCode();

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
	        TOParticipantCost top = new TOParticipantCost(participantEmail, participantName, totalCostForBookableItems, totalCostForSnowTour);

	        // Each time we're going through a participant and getting the respective information, we're
	        // adding it to the list allParticipantCosts
	        allParticipantCosts[i] = top;
	        i++;

	      }


	      // We create a new object of type TOSnowShoeTour with the respective parameters we got previously
	      TOSnowShoeTour TheSnowShoeTour = new TOSnowShoeTour(id, startWeek, endWeek, guideEmail, guideName,
	          totalCostForGuide, allParticipantCosts);

	      // return the TOSnowShoeTour TheSnowShoeTour
	      return TheSnowShoeTour;

	    }

	    catch (RuntimeException e) {
	      error = e.getMessage(); // will catch exceptions errors and get the respective error message
	    }

	    if (!error.isEmpty()) {
	      return null; // We don't have errors to return
	    }
	    return null;
  }

  public static String updateSnowShoeTour(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
	// TODO Implement the method, return error message (if any)
	    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
	    
	    //input validation
	    if(nrWeeks < 0) return "The number of riding weeks must be greater than or equal to zero";
	    if(priceOfGuidePerWeek<0) return "The price of guide per week must be greater than or equal to zero";
	    if(startDate.getYear()<sst.getStartDate().getYear()) return "The start date cannot be from previous year or earlier";
	    
	    sst.setStartDate(startDate);
	    sst.setNrWeeks(nrWeeks);
	    sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
	    
	    return "";
  }
}