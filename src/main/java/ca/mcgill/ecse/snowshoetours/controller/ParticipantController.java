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
		  return "Email cannot be empty";
	  }
	  if (password == "" || password == null) {
		  return "Password cannot be empty";
	  }
	  if (name == "" || name == null) {
		  return "Name cannot be empty";
	  }
	  if (emergencyContact == "" || emergencyContact == null) {
		  return "Emergency contact cannot be empty";
	  }
	  if (nrWeeks <= 0) {
		  return "Number of weeks must be greater than zero";
	  }
	  if (nrWeeks > 10) {
		  return "Number of weeks must be less than or equal to the number of biking weeks in the biking season";
	  }
	  if (weekAvailableFrom < 1 || weekAvailableFrom > 10) {
		  return "Available weeks must be within weeks of biking season (1-10)";
	  }
	  if (weekAvailableUntil < 1 || weekAvailableUntil > 10) {
		  return "Available weeks must be within weeks of biking season (1-10)";
	  }
	  //Logic check
	  if (weekAvailableFrom >= weekAvailableUntil) {
		  return "Week from which one is available must be less than or equal to the week until which one is available";
	  }
	  if (email.equals("manager@btp.com")) {
		  return "Email cannot be manager@btp.com";
	  }
	  if (email.contains(" ")) {
		 return "Email must not contain any spaces";
	  }
	  if (nrWeeks > (weekAvailableUntil - weekAvailableFrom)){
		 return "Number of weeks must be less than or equal to the number of available weeks";
	  }
	  //Valid email address verifier
	  if (!(email.contains("mail"))|| !(email.contains("@"))) {
		  return "Invalid email";
	  }
	  if (email.contains(".ca")|| email.contains(".com")) {
	  }
	  else {return "Invalid email";}
	  if ( !(email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@") && email.indexOf("@") < email.lastIndexOf(".") - 1 && email.lastIndexOf(".") < email.length() - 1)) return "Invalid email";
	  
	  //I AM NOT SURE IF THAT IS ALREADY COVERED BY THE UNIQUE KEYWORD OF UMPLE
	  SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
	  List<Participant> participants = sst.getParticipants();
	  List<Guide> guides = sst.getGuides();
	  if (sst.getManager().getAccountName().equals(email)) {
		  return "";
	  }
	  for (Participant participant : participants) {
		  if (participant.getAccountName().equals(email)) {
			  return "Email already linked to a participant account";
		  }
<<<<<<< HEAD
		  for (Guide guide : guides) {
		    if (participant.getAccountName().equals(guide.getAccountName())) {
			  return "Email already linked to a guide account";
		    }
		  }
=======
		  if (!(sst.getManager() == null)) {
			  if (participant.getAccountName().equals(sst.getManager().getAccountName())){
				  return "The email address is already used";
		  }
		
		  }
		  
>>>>>>> aa4bdcbf9d56ad9414b4b513a45233166c4bbbf0
	  }
	  for (Guide guide : guides) {
        if (guide.getAccountName().equals(email)) {
          return "Email already linked to a guide account";
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
   * @throws RunTimeException
   */
  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
	 
	//Basic check
    if (email == "" || email == null) {
      return "The email cannot be empty";
    }
    if (bookableItemName == "" || bookableItemName == null) { //Added
    	return "The bookable item name must not be empty";
    }
    //Checking if email belongs to a participant
    User user = User.getWithAccountName(email);
    if (!(user instanceof Participant) || user == null) {
    	return "The participant does not exist";
    }
    Participant participant = (Participant) user;
    
    //Checking if bookable item exists in the system
    if (!(BookableItem.hasWithName(bookableItemName))) {
    	return "The piece of gear or combo does not exist";
    }
    
    
    BookableItem item = BookableItem.getWithName(bookableItemName);
    //Checking if participant already has the item
    //Successfully Increasing Qty participant already had the item
	List<BookedItem> bookedItems = participant.getBookedItems();
	for (BookedItem b : bookedItems) {
		if(b.getItem().equals(item)) {
			b.setQuantity(b.getQuantity() + 1);
			return "";
		}
	}
	//Successfully adding the new item
	SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); //Added
	participant.addBookedItem(1,sst, item);//Is there a cleaner way?
	return "";
    }
  
  /**
   * @author Wasif Somji
   * 
   * @param email
   * @param bookableItemName
   * @return
   */
  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
	  
	  if (email == "" || email == null) {
		  return "Email must exist.";
	  }
	  
	  if (bookableItemName == "" || bookableItemName == null) {
		  return "Bookable item must exist."; 
	  }
	  var error = "";
	  
	  BookableItem bookedItem = BookableItem.getWithName(bookableItemName); 
	    
	  User user = User.getWithAccountName(email);
	    // Check if user is null or not an instance of participant
	  
	    if (!(user instanceof Participant)) {
	      return "The participant does not exist";
	    }
	    
	    // Check if item is null
	    if (bookedItem == null) {
	      return "The piece of gear or combo does not exist";
	    }
	    
	    try {
	        Participant p = (Participant) user;
	        // Get all booked items for participant
	        List <BookedItem> bi = p.getBookedItems();
	        // Check if given bookable item is a booked item
	        for (BookedItem b : bi) {
	          if (b.getItem() == bookedItem) {
	            int currentQuantity = b.getQuantity();
	            if (currentQuantity > 1) {
	              b.setQuantity(currentQuantity - 1); // Decrease quantity by 1
	            } else {
	              p.removeBookedItem(b); // Remove the booked item from participant
	              b.delete();
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


