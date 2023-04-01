package ca.mcgill.ecse.snowshoetours.controller;

import java.util.*;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.BookableItem;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.User;
import ca.mcgill.ecse.snowshoetours.persistence.SstPersistence;

public class ParticipantController {
	/**
	   * @author Philippe Marchand+Martin Eskaros
	   * @param email
	   * @param password
	   * @param name
	   * @param emergencyContact
	   * @param nrWeeks
	   * @param weekAvailableFrom
	   * @param weekAvailableUntil
	   * @param lodge required
	   * @return error message
	   */
  public static String registerParticipant(String email, String password, String name,
      String emergencyContact, int nrWeeks, int weekAvailableFrom, int weekAvailableUntil,
      boolean lodgeRequired) {
	  
	  if ( email==null|| email.isBlank()) {
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }
	    // checks for validity of email
		return "Email cannot be empty";
	  }
	  if ( password == null || password.isBlank()) {
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }// checks for validity of password
	    return "Password cannot be empty";
	  }
	  if ( name == null || name.isBlank()) {
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }
	    // checks for validity of name
	    return "Name cannot be empty";
	  }
	  if ( emergencyContact == null || emergencyContact.isBlank()) {
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }
	    // checks for validity of emergency contact
		return "Emergency contact cannot be empty";
	  }
	  if (nrWeeks <= 0) { // checks for validity of the integer number of weeks (in bounds)
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Number of weeks must be greater than zero";
	  }
	  if (nrWeeks > 10) { // checks for validity of the integer number of weeks (in bounds)
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Number of weeks must be less than or equal to the number of snowshoe weeks in the snowshoe season";
	  }
	  if ((weekAvailableFrom < 1 || weekAvailableFrom > 10) || (weekAvailableUntil < 1 || weekAvailableUntil > 10)) { // checks weekAvailableFrom & weekAvailableUntil are within the bounds
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Available weeks must be within weeks of snowshoe season (1-10)";
	  }
	  //Logic check
	  if (weekAvailableFrom >= weekAvailableUntil) { // checks for correct logic of week available dates
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Week from which one is available must be less than or equal to the week until which one is available";
	  }
	  if (email.equals("manager")) { // checks for validity of email
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Invalid email";
	  }
	  if (email.contains(" ")) { // checks for empty string in email (i.e. spaces)
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    } 
	    return "Email must not contain any spaces";
	  }
	  if (nrWeeks > (weekAvailableUntil - weekAvailableFrom +1)){ // checks number of weeks is equal to the weeks available + 1
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    } 
	    return "Number of weeks must be less than or equal to the number of available weeks";
	  }
	  
	  if (!(email.contains("mail"))|| !(email.contains("@"))) { // checks for validity of email
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Invalid email";
	  }

	  // checks for validity of email using the sequence of characters in the string
	  if ( !(email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@") && email.indexOf("@") < email.lastIndexOf(".") - 1 && email.lastIndexOf(".") < email.length() - 1)) return "Invalid email";
	  
	  //I AM NOT SURE IF THAT IS ALREADY COVERED BY THE UNIQUE KEYWORD OF UMPLE
	  SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
	  List<Participant> participants = sst.getParticipants(); //assigns variable participants
	  List<Guide> guides = sst.getGuides(); // assigns variable guides
	  if (sst.getManager().getAccountName().equals(email)) { // checks manager's email in system and returns empty string if match occurs
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "";
	  }
	  for (Participant participant : participants) {
		  if (participant.getAccountName().equals(email)) { // if the participant's information already exists in the system
		    try {
		      SstPersistence.save();
		    } catch (RuntimeException e) {
		      return e.getMessage();
		    }  
		    return "Email already linked to a participant account"; 
		  }
	  }
	  for (Guide guide : guides) {
		    if (email.equals(guide.getAccountName())) { // if the guide's information already exists in the system
		      try {
		        SstPersistence.save();
		      } catch (RuntimeException e) {
		        return e.getMessage();
		      }
		      return "Email already linked to a guide account";
		    }
		  }
	  //Once all conditions are checked, successfully add the participant
	  sst.addParticipant(email,password,name,emergencyContact,nrWeeks,weekAvailableFrom, weekAvailableUntil, 
			  lodgeRequired,"",0);
	  try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }
	  return "";
  }
  /**
   * @author Philippe Marchand
   * @param email
   */
  public static void deleteParticipant(String email) {
     // TODO Implement the method
	//Basic input check
	  if ( email == null || email.isBlank()) {
	  }
	 //Successfully removes the participant 
	 // or does nothing there is not a participant with the input email 
	  else {
		  SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
		  List<Participant> participants = sst.getParticipants();
		  for (Participant participant : participants) { 
			  if (participant.getAccountName().equals(email)) { // once the email matches the one in the system
				  participant.delete(); // you can successfully delete it
				  break;
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
   */
  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
	 
	//Basic check
    if (email == null || email.isBlank()) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return e.getMessage();
      }
      return "The email cannot be empty";
    }
    if ( bookableItemName == null|| bookableItemName.isBlank()) { //Added
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return e.getMessage();
      }
      return "The bookable item name must not be empty";
    }
    //Checking if email belongs to a participant
    User user = User.getWithAccountName(email);
    if (user == null|| !(user instanceof Participant)  ) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return e.getMessage();
      }
      return "The participant does not exist";
    }
    Participant participant = (Participant) user;

    //Checking if bookable item exists in the system
    if (!(BookableItem.hasWithName(bookableItemName))) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        return e.getMessage();
      }
      return "The piece of gear or combo does not exist";
    }
    
    
    BookableItem item = BookableItem.getWithName(bookableItemName);
    //Checking if participant already has the item
    //Successfully Increasing Qty participant already had the item
	List<BookedItem> bookedItems = participant.getBookedItems();
	for (BookedItem b : bookedItems) {
		if(b.getItem().equals(item)) {
		  try {
		      SstPersistence.save();
		  } catch (RuntimeException e) {
		      return e.getMessage();
		  }
		  b.setQuantity(b.getQuantity() + 1);
		  return "";
		}
	}
	//Successfully adding the new item
	SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); //Added
	participant.addBookedItem(1,sst, item);//Is there a cleaner way?
	try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      return e.getMessage();
    }
	return "";
    }
  
  /**
   * @author Wasif Somji
   * removes a bookableItem from Participant
   * @param email
   * @param bookableItemName
   * @return error message
   */
  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
	  
	  if ( email == null || email.isBlank()) { // checks validity of email
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Email must exist.";
	  }
	  
	  if (bookableItemName == null || bookableItemName.isBlank()) { // checks validity of the bookable item name
	    try {
	      SstPersistence.save();
	    } catch (RuntimeException e) {
	      return e.getMessage();
	    }  
	    return "Bookable item must exist."; 
	  }
	  var error = ""; //sets empty string for error
	  
	  BookableItem bookedItem = BookableItem.getWithName(bookableItemName); // assigns variable bookedItem
	    
	  User user = User.getWithAccountName(email); // assigns user to the email given in input
	    
	    if (!(user instanceof Participant)) { // Check if user is null or not an instance of participant
	      try {
	        SstPersistence.save();
	      } catch (RuntimeException e) {
	        return e.getMessage();
	      }
	      return "The participant does not exist";
	    }
	    
	    // Check if item is null
	    if (bookedItem == null) { // checks validity of the booked item
	      try {
	        SstPersistence.save();
	      } catch (RuntimeException e) {
	        return e.getMessage();
	      }
	      return "The piece of gear or combo does not exist";
	    }
	    
	    try { // try catch loop to check errors if needed
	        Participant p = (Participant) user;
	        // Get all booked items for participant
	        List <BookedItem> bi = p.getBookedItems();
	        // Check if given bookable item is a booked item
	        for (BookedItem b : bi) {
	          if (b.getItem() == bookedItem) {
	            int currentQuantity = b.getQuantity(); 
	            if (currentQuantity > 1) { // quantity should be more than 1 to avoid any running out of quantity (can't go in negatives in real life)
	              b.setQuantity(currentQuantity - 1); // Decrease quantity by 1
	            } else {
	              p.removeBookedItem(b); // Remove the booked item from participant
	              b.delete();
	            }
	            try {
	              SstPersistence.save();
	            } catch (RuntimeException e) {
	              return e.getMessage();
	            }
	            return "";
	          }
	        }
	        // If bookable item was not a booked item, the function will not return in
	        // above while loop, then return an error message
	        error = "The participant did not book the item";
	      } catch (RuntimeException e) {
	        error = e.getMessage();
	      }
	      return error;
	
  }
}


