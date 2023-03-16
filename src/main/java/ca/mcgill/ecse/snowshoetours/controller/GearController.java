package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class GearController {
  public static String addGear(String name, int pricePerWeek) {
    //add a piece of gear unsuccessfully
    // check validity of name and price
    if(pricePerWeek < 0 ) {
      return "The price per week must be greater than or equal to 0";
    }
    if(name.isEmpty()|| name == null) {
      return "The name must not be empty ";
    }
    
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); 
    List<Gear> gears = sst.getGear();
    //check name similiarity with gear names
    for(Gear gear : gears) {
      if(gear.getName().equals(name)) return "A piece of gear with the same name already exists";
    }
    
    List<Combo> combos = sst.getCombos();
    //check name similarity with the combos name
    for(Combo combo : combos) {
      if(combo.getName().equals(name)) return "A combo with the same name already exists";
    }
    
    //add a piece of gear sucessfully
    sst.addGear(sst.addGear(name, pricePerWeek));
    
    return "";
  }

  public static String deleteGear(String name) {
    
    return "Not implemented!";
  }

  public static String addCombo(String name, int discount) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }

  public static void deleteCombo(String name) {
    // TODO Implement the method
  }

  // this method does not need to be implemented by a team with five team members
  public static String addGearToCombo(String gearName, String comboName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }

  // this method does not need to be implemented by a team with five team members
  public static String removeGearFromCombo(String gearName, String comboName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }
}
