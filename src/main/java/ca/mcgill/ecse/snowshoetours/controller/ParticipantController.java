package ca.mcgill.ecse.snowshoetours.controller;

public class ParticipantController {
  public static String registerParticipant(String email, String password, String name,
      String emergencyContact, int nrWeeks, int weekAvailableFrom, int weekAvailableUntil,
      boolean lodgeRequired) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }

  public static void deleteParticipant(String email) {
     // TODO Implement the method
  }

  public static String addBookableItemToParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
    if (email == "" || email == null) {
      return "The email cannot be empty."
  }
    if (bookableItemName = "" || bookableItemName == null) {
      return "The bookable item name must not be empty. 
  }
    

  public static String removeBookableItemFromParticipant(String email, String bookableItemName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }
}
