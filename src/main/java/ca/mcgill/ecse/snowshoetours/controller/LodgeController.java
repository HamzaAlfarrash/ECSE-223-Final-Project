package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Lodge;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class LodgeController {
  private static SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
  
  public static String addLodge(String name, String address, int nrStars) {
    if (name == null || name.isEmpty()) return "Name cannot be empty";  
    if (name == null || name.isEmpty()) return "Address cannot be empty";
    
    //Check the rating guys it should not be null
    sst.addLodge(sst.addLodge(name, address, null));
    return null;
  }

  // this method does not need to be implemented by a team with five team members
  public static void deleteLodge(String name) {
    
    List<Lodge> lodges = sst.getLodges();
    Lodge aLodge = null;
    for( Lodge lodge : lodges) {
      if ( lodge.getName().equals(name)) aLodge = lodge;
    }
    
    if(aLodge != null) aLodge.delete();
  }
}
