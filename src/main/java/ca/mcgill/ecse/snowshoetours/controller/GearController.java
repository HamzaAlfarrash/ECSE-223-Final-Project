package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class GearController {
  
  /**
   * @author Souhail El Hayani
   * @param name
   * @param pricePerWeek
   * @return
   */
  public static String addGear(String name, int pricePerWeek) {
    //add a piece of gear unsuccessfully
    // check validity of name and price
    if(pricePerWeek < 0 ) {
      return "The price per week must be greater than or equal to 0";
    }
    if(name == null || name.isBlank() ) {
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
    
    return null;
  }

  /**
   * @author Souhail El Hayani
   * @param name
   * @return
   */
  public static String deleteGear(String name) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    //get gear with corresponding name
    Gear aGear = null;
    List<Gear> gears = sst.getGear();
    for(Gear gear: gears) {
      if(gear.getName().equals(name)) aGear = gear;
    }
    if(aGear == null) return "gear with name: "+name+" ,doesn't exist";
    
    //unsuccesfully delete a gear that is in an existing combo
    List<Combo> combos = sst.getCombos();
    for(Combo combo:combos) {
      List<ComboItem> items = combo.getComboItems();
      for(ComboItem item:items) {
        if(item.getGear().getName().equals(aGear.getName())) return "The piece of gear is in a combo and cannot be deleted";
      }
    }
    
    //successfully delete a piece of gear
    aGear.delete();//if participant has the gear, delete it from his bookedItems, delete takes care of referential integrity
    return null;
  }

  /**
   * @author Yassine Mimet
   * 
   * @param name
   * @param discount
   * @return
   */
  public static String addCombo(String name, int discount) {
    if(discount < 0 ) {
      return "Discount must be at least 0";
    }
    
    if(discount > 100 ) {
      return "Discount must be no more than 100";
    }

    if(name == null || name.isBlank() ) {
      return "The name must not be empty ";
    }
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); 
    
    List<Combo> combos = sst.getCombos();
    for(Combo combo : combos) {
      if(combo.getName().equals(name)) return "A combo with the same name already exists";
    }
    
    List<Gear> gears = sst.getGear();
    for(Gear gear : gears) {
      if(gear.getName().equals(name)) return "A piece of gear with the same name already exists";
    }
    
    sst.addCombo(sst.addCombo(name, discount));
    
    return null;
  }

  public static void deleteCombo(String name) {
    // not done yet
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    
    Combo combo = null;
    
  }

  // this method does not need to be implemented by a team with five team members
  /**
   * @author Martin Eskaros
   *
   * @param gearName
   * @param comboName
   * @return
   */
  public static String addGearToCombo(String gearName, String comboName) {

    if(gearName.isBlank()) {
      return "The gear name must not be empty ";
    }
    if(comboName.isBlank()) {
      return "The comboName name must not be empty ";
    }
    
    boolean comboExists = false;
    boolean gearExists =false;
    ComboItem gearComboItem=null;
    Combo tCombo =null;
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Combo> allCombos = sst.getCombos();    //all combos in the Snow Shoe Tour
    for (Combo combo : allCombos){              //iterate through all combos + check if combo exists
      if(combo.getName().equals(comboName)){
        tCombo =combo;
        comboExists=true;
        break;
      }
    }
    //Combo doesnt exist
    if(!comboExists){
      return "There is not a combo with the name "+ comboName + " in the system";
    }

    List<Gear> gears = sst.getGear();   //get all gears in the Snow Shoe Tour
    Gear tGear = null;
    for (Gear gear : gears) {           //check if gear exists
      if (gear.getName().equals(gearName)) {
        tGear = gear;
        gearExists=true;
        break;
      }
    }
    //Gear doesnt exist
    if(!gearExists){
      return "There is no gear with the name "+gearName+" in the system";
    }
    gearComboItem = new ComboItem(1,sst,tCombo,tGear);    //if gear exists, then we want it to be a comboItem so we can add it to our combo.

    //Add combo item (gear) to the combo.
    tCombo.addComboItem(gearComboItem);

    return null;
  }

  // this method does not need to be implemented by a team with five team members
  public static String removeGearFromCombo(String gearName, String comboName) {
    // TODO Implement the method, return error message (if any)
    return "Not implemented!";
  }
}
