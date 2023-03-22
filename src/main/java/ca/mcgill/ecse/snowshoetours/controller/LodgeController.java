package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Lodge;
import ca.mcgill.ecse.snowshoetours.model.Lodge.LodgeRating;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class LodgeController {
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
  
  public static String addLodge(String name, String address, int nrStars) {
    //input check
    if (name == null || name.isEmpty()) return "Name cannot be empty";  //checks validity of name
    if (name == null || name.isEmpty()) return "Address cannot be empty"; //checks validity of ...
    
    LodgeRating rating;
    
    //get the rating from the number of stars
    switch(nrStars) {
      case 1:
        rating = LodgeRating.OneStar; // if number of stars is 1
        break;
      case 2:
        rating = LodgeRating.TwoStars; // if number of stars is 2
        break;
      case 3:
        rating = LodgeRating.ThreeStars; // if number of stars is 3
        break;
      case 4:
        rating = LodgeRating.FourStars; // if number of stars is 4
        break;
      case 5:
        rating = LodgeRating.FiveStars; // if number of stars is 5
        break;
      default:
        return "Rating should be between 1 and 5"; // if otherwise, means rating is not correct / in bounds
    }
    
    //Check the rating guys it should not be null
    sst.addLodge(sst.addLodge(name, address, rating)); //adds to SnowSheTour
    
    return null;
  }

  public static void deleteLodge(String name) {
    
    List<Lodge> lodges = sst.getLodges(); // attains variable Lodges
    Lodge aLodge = null;
    for( Lodge lodge : lodges) {
      if ( lodge.getName().equals(name)) aLodge = lodge; // checks through system and sets variables equal upon match
    }
    
    if(aLodge != null) aLodge.delete(); // if aLodge is not null, it can be successfully deleted
  }
}
