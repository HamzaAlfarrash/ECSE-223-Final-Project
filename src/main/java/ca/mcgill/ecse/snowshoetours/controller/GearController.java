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
    
    if(pricePerWeek < 0 ) { //checks validity of price
      return "The price per week must be greater than or equal to 0";
    } else if(name == null || name.isBlank()) { //checks validity of name
      return "The name must not be empty";
    } else {
      SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); //assigns variable sst (this will not be redocumented in later methods to avoid redundancy)
      List<Gear> gears = sst.getGear(); // gets the list of gear with variable gears
      //check name similiarity with gear names
      for(Gear gear : gears) {
        if(gear.getName().equals(name)) return "A piece of gear with the same name already exists"; // if it's a repeat name
      }
      
      List<Combo> combos = sst.getCombos(); // gets the list of combo with variable combos (this will not be redocumented in later methods to avoid redundancy)
      //check name similarity with the combos name
      for(Combo combo : combos) {
        if(combo.getName().equals(name)) return "A combo with the same name already exists"; // if it's a repeat combo
      }
      
      sst.addGear(sst.addGear(name, pricePerWeek)); //add a piece of gear sucessfully
      
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
    for(Gear gear: gears) { // if the gear exists
      if(gear.getName().equals(name)) aGear = gear;
    }
    if(aGear == null) return "gear with name: "+name+" ,doesn't exist";
    
    //unsuccesfully delete a gear that is in an existing combo
    List<Combo> combos = sst.getCombos();
    for(Combo combo:combos) {
      List<ComboItem> items = combo.getComboItems();
      for(ComboItem item:items) { //if there is the piece of gear in the combo, it cannot be deleted
        if(item.getGear().getName().equals(name)) return "The piece of gear is in a combo and cannot be deleted";
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
    if(discount < 0 ) { =
      return "Discount must be at least 0";
    }
    //the if statements above and below this comment check 0 < discount < 100
    if(discount > 100 ) {
      return "Discount must be no more than 100";
    }

    if(name == null || name.isBlank() ) { //checks for validity
      return "The name must not be empty ";
    }
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); 
    
    List<Combo> combos = sst.getCombos();
    for(Combo combo : combos) { //if the combo names is not unique
      if(combo.getName().equals(name)) return "A combo with the same name already exists";
    }
    
    List<Gear> gears = sst.getGear();
    for(Gear gear : gears) { // if a piece of gear already exists with the same name
      if(gear.getName().equals(name)) return "A piece of gear with the same name already exists";
    }
    
    sst.addCombo(sst.addCombo(name, discount)); //successfully adds the combo with the discount
    
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
    for(Combo combo : combos) { // if the combo matches the same name as another combo
      if(combo.getName().equals(name)) aCombo = combo;
    }
    if(aCombo == null) return;
    
    if (aCombo != null) aCombo.delete(); //if the combo is null, successfully deletes the combo
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

    if(gearName.isBlank()) { // if the gear name is empty
      return "The gear name must not be empty ";
    }
    if(comboName.isBlank()) { // if the combo name is empty
      return "The comboName name must not be empty ";
    }
    
    boolean comboExists = false; //initializing variables
    boolean gearExists =false;
    ComboItem gearComboItem=null;
    Combo tCombo =null;
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Combo> allCombos = sst.getCombos();    //all combos in the Snow Shoe Tour
    for (Combo combo : allCombos){              //iterate through all combos + check if combo exists
      if(combo.getName().equals(comboName)){    // if combo exists, tCombo to combo and set comboExists to true
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
    
    //if gear already is in the combo, then just increment the quantity
    for(ComboItem item : tCombo.getComboItems()) {
      if(item.getGear().getName().equals(tGear.getName())) {
        int q = item.getQuantity();
        q++;
        item.setQuantity(q);
        return "";
      }
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
        comboItem.setQuantity(comboItem.getQuantity()-1);
        if(comboItem.getQuantity()<1) {
          if(tCombo.getComboItems().size()<=2) { // if the combo only has one piece of gear
            comboItem.setQuantity(comboItem.getQuantity()+1);
            return "A combo must have at least two pieces of gear";
          }
          comboItem.delete(); //deletse the combo item
        }
        return "";
      }
    }

    return "";
  }
}
