package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Manager;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class GuideController {
  
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
  
  /**
   * @author Yassine Mimet
   * 
   * @param email
   * @param password
   * @param name
   * @param emergencyContact
   * @return
   */
  public static String registerGuide(String email, String password, String name,
      String emergencyContact) {
    if (email == null || email.isEmpty()) return "Email cannot be empty"; // checks for validity of email
    
    if (password == null || password.isEmpty()) return "Password cannot be empty"; //checks for validity of password

    if (name == null || name.isEmpty()) return "Name cannot be empty"; // checks for validity of name

    // checks for validity of emergency contact
    if (emergencyContact == null || emergencyContact.isEmpty()) return "Emergency contact cannot be empty";

    List<Guide> Guides = sst.getGuides(); // sets variable Guides
    Guide aGuide = null;
    for (Guide guide : Guides) { // checks if Guide's account corresponds to the email, sets them equal if so
      if (guide.getAccountName().equals(email)) aGuide = guide;
    }
    if (aGuide != null) return "Email already linked to a guide account"; // if the aGuide already exists

    
    List<Participant> participants = sst.getParticipants(); // sets variable participants
    Participant aParticipant = null; // initializes
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(email)) aParticipant = participant; // checks system like before to set variables equal
    }
    if (aParticipant != null) return "Email already linked to a participant account"; // if the participant's email already exists
    
    if (email.contains(" ")) return "Email must not contain any spaces"; // if the email contains a blank string (i.e. a space)

    //checks validity of email
    if ( !(email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@") && email.indexOf("@") < email.lastIndexOf(".") - 1 && email.lastIndexOf(".") < email.length() - 1)) return "Invalid email"; 
    
    sst.addGuide(sst.addGuide(email, password, name, emergencyContact)); //adds the guide to the SnowShoeTour

    return "";
  }

  /**
   * @author Yassine Mimet
   * 
   * @param email
   */
  public static void deleteGuide(String email) {
    List<Guide> Guides = sst.getGuides();
    Guide aGuide = null;
    for (Guide guide : Guides) {
      if (guide.getAccountName().equals(email)) aGuide = guide; //checks the system as before 
    }
    
    if(aGuide != null) aGuide.delete(); // if the Guide exists, you can delete it
  }
}
