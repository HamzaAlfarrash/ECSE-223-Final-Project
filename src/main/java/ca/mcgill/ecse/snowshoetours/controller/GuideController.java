package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
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
    if (email == null || email.isEmpty()) return "Email cannot be empty";
    
    if (password == null || password.isEmpty()) return "Password cannot be empty";

    if (name == null || name.isEmpty()) return "Name cannot be empty";


    if (emergencyContact == null || emergencyContact.isEmpty()) return "Emergency contact cannot be empty";

    List<Guide> Guides = sst.getGuides();
    Guide aGuide = null;
    for (Guide guide : Guides) {
      if (guide.getAccountName().equals(email)) aGuide = guide;
    }
    if (aGuide != null) return "Email already linked to a guide account";

    
    List<Participant> participants = sst.getParticipants();
    Participant aParticipant = null;
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(email)) aParticipant = participant;
    }
    if (aParticipant != null) return "Email already linked to a participant account";
    
    
    if (email.equals("manager@btp.com")) return "Email cannot be manager@btp.com";
    
    
    if (email.contains(" ")) return "Email must not contain any spaces";

    if ( !(email.contains("@") && email.indexOf("@") > 0 && email.indexOf("@") == email.lastIndexOf("@") && email.indexOf("@") < email.lastIndexOf(".") - 1 && email.lastIndexOf(".") < email.length() - 1) ) return "Invalid email"; 
    
    sst.addGuide(sst.addGuide(name, password, name, emergencyContact));
    
    return null;
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
      if (guide.getAccountName().equals(email)) aGuide = guide;
    }
    
    if(aGuide != null) aGuide.delete();
  }
}
