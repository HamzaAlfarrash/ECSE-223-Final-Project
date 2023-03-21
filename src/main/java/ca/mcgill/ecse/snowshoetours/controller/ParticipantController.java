package ca.mcgill.ecse.snowshoetours.controller;

import java.util.*;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;

public class ParticipantController {
	/**
	   * @author Philippe Marchand
	   * @param email
	   * @param password
	   * @param name
	   * @param emergencyContact
	   * @param nrWeeks
	   * @param weekAvailableFrom
	   * @param weekAvailableUntil
	   * @param lodge required
	   * @return
	   */
  public static String registerParticipant(String email, String password, String name,
      String emergencyContact, int nrWeeks, int weekAvailableFrom, int weekAvailableUntil,
      boolean lodgeRequired) {
	  // TODO Implement the method, return error message (if any)
	  //Basic input check
	  if (email == "" || email == null) {
		  return "The email must not be empty";
	  }
	  if (password == "" || password == null) {
		  return "The password must not be empty";
	  }
	  if (name == "" || name == null) {
		  return "The name must not be empty";
	  }
	  if (emergencyContact == "" || emergencyContact == null) {
		  return "The emergency contact must not be empty";
	  }
	  if (nrWeeks <= 0) {
		  return "The number of weeks of stay must be greater than 0";
	  }
	  if (weekAvailableFrom < 0) {
		  return "The number of the week available from must be greater than 0";
	  }
	  if (weekAvailableUntil < 0) {
		  return "The number of the week available until must be greater than 0";
	  }
	  //Logic check
	  if (weekAvailableFrom >= weekAvailableUntil) {
		  return "The number of the week available from should be smaller than the week available until";
	  }
	  
	  //I AM NOT SURE IF THAT IS ALREADY COVERED BY THE UNIQUE KEYWORD OF UMPLE
	  SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
	  List<Participant> participants = sst.getParticipants();
	  List<Guide> guides = sst.getGuides();
	  for (Participant participant : participants) {
		  if (participant.getAccountName().equals(email)) {
			  return "The email address is already registered";
		  }
		  if (participant.getAccountName().equals(sst.getManager().getAccountName())) {
			  return "The email address is already used";
		  }
		  for (Guide guide : guides) {
		  if (participant.getAccountName().equals(guide.getAccountName())) {
			  return "The email address is already used";
		  }
	  }
		  }
	  //Add successfully the participant
	  sst.addParticipant(email,password,name,emergencyContact,nrWeeks,weekAvailableFrom, weekAvailableUntil, 
			  lodgeRequired,"",0);
	  return "";
  }
  /**
   * @author Philippe Marchand
   * @param email
   */
  public static void deleteParticipant(String email) {
     // TODO Implement the method
	//Basic input check
	  if (email == "" || email == null) {
	  }
	 //Successfully removes the participant 
	 // or does nothing there is not a participant with the input email 
	  else {
		  SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
		  List<Participant> participants = sst.getParticipants();
		  for (Participant participant : participants) {
			  if (participant.getAccountName().equals(email)) {
				  participant.delete();
			  }
		  }
	  }
  }
  /**
   * @author Hamza Alfarrash
   * adds a bookable item to Participant
   * @param email
   * @param bookableItemName
   * @return error message (if any)
   * @throws RunTimeException
   */
  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
    if (email == "" || email == null) {
      return "The email cannot be empty";
    }
    if (bookableItemName == "" || bookableItemName == null) { //Added
    	return "The bookable item name must not be empty";
    	}
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); //Added
    
	  var error = "";
	    BookableItem item = BookableItem.getWithName(bookableItemName);
	    User user = User.getWithAccountName(email); //Edited
	    // Check if user is null or not an instance of participant
	    if (user == null || !(user instanceof Participant)) {
	      return "The participant does not exist";
	    }
	    // Check if item is null
	    if (item == null) {
	      return "The piece of gear or combo does not exist";
	    }

	    try {
	      Participant p = (Participant) user;
	      // Get all booked items for participant
	      List<BookedItem> bi = p.getBookedItems();
	      // Check if given bookable item is already a booked item
	      for (BookedItem b : bi) {
	        if (b.getItem() == item) {
	          b.setQuantity(b.getQuantity() + 1); // Increase quantity by 1
	          return "";
	        }
	      }
	      // If bookable item was not a booked item, the function will not return in
	      // above while loop, then add a booked item to p
	      p.addBookedItem(1, sst, item);

	    } catch (RuntimeException e) {
	      error = e.getMessage();
	      return error;
	    }  
	    return "";
    }

  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }
}