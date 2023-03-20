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
   * @return error message
   */
  public static String addGear(String name, int pricePerWeek) {
    //add a piece of gear unsuccessfully
    // check validity of name and price
    
    if(pricePerWeek < 0 ) {
      return "The price per week must be greater than or equal to 0";
    } else if(name == null || name.isBlank()) {
      return "The name must not be empty";
    } else {
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
  }

  /**
   * @author Souhail El Hayani
   * @param name
   * @return error message
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
    aGear.delete();
    return "";
  }

  /**
   * @author Yassine Mimet
   * 
   * @param name
   * @param discount
   * @return error message
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
    
    return "";
  }
    
  /**
   * @author Yassine Mimet
   * @param name
   */
  public static void deleteCombo(String name) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    
    Combo aCombo = null;
    List<Combo> combos = sst.getCombos();
    for(Combo combo : combos) {
      if(combo.getName().equals(name)) aCombo = combo;
    }
    
    aCombo.delete();
  }

  // this method does not need to be implemented by a team with five team members
  /**
   * @author Martin Eskaros
   *
   * @param gearName
   * @param comboName
   * @return error message
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
      return "The combo does not exist";
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
      return "The piece of gear does not exist";
    }
    gearComboItem = new ComboItem(1,sst,tCombo,tGear);    //if gear exists, then we want it to be a comboItem so we can add it to our combo.

    //Add combo item (gear) to the combo.
    tCombo.addComboItem(gearComboItem);
    //Add combo item to the list of combo items in the gear
    tGear.addComboItem(gearComboItem);

    return "";
  }

  /**
   * @author Wasif Somji
   *
   * @param gearName
   * @param comboName
   * @return error message
   */
  public static String removeGearFromCombo(String gearName, String comboName) {
    // TODO Implement the method, return error message (if any)
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
      return "The combo does not exist";
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
      return "The piece of gear does not exist";
    }
    for (ComboItem comboItem : tCombo.getComboItems()) {  //iterate over combo items in desired combo
      if (comboItem.getGear() == tGear) {                 //if the combo item is of the associated gear, remove it from the combo.
        boolean removed = tCombo.removeComboItem(comboItem); //the boolean is false if combo only has 2 pieces of gear to begin with
        if(!removed) {
          return "A combo must have at least two pieces of gear";
        }
        tGear.removeComboItem(comboItem);                 //remove combo item from the list of combo items in the gear
      }
    }

    return "";
  }
}
