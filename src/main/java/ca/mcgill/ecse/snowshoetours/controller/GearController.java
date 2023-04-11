package ca.mcgill.ecse.snowshoetours.controller;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.persistence.SstPersistence;

public class GearController {

  /**
   * @author souhail el hayani
   * @return list of transfer objects of gear
   */
  public static List<TOGears> getGears() {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<TOGears> list = new ArrayList<>();
    List<Gear> list1 = sst.getGear();
    for(Gear gear : list1) {
      list.add(new TOGears( gear.getPricePerWeek(),gear.getName()));
    }
    return list;
  }
  
  /**
   * @author souhail el hayani
   * @return list of transfer objects of combo
   */
  public static List<TOCombo> getCombos() {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<TOCombo> list = new ArrayList<>();
    List<Combo> list1 = sst.getCombos();
    for(Combo combo : list1) {
    //get the comboItems of the combo
      List<TOGears> gears = new ArrayList<>();
      for(ComboItem item : combo.getComboItems()) {
        Gear gear = item.getGear();
        gears.add(new TOGears(gear.getPricePerWeek(), gear.getName()));
      }
      list.add(new TOCombo(combo.getName(), combo.getDiscount(), gears));
    }
    return list;
  }
  
  /**
   * @author Souhail El Hayani adds a gear
   * @param name
   * @param pricePerWeek
   * @return error message
   */
  public static String addGear(String name, int pricePerWeek) {
    // add a piece of gear unsuccessfully
    // check validity of name and price

    if (pricePerWeek < 0) { // checks validity of price
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The price per week must be greater than or equal to 0";
    } else if (name == null || name.isBlank()) { // checks validity of name
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The name must not be empty";
    } else {
      SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour(); // assigns variable sst (this
                                                                     // will not be redocumented in
                                                                     // later methods to avoid
                                                                     // redundancy)
      List<Gear> gears = sst.getGear(); // gets the list of gear with variable gears
      // check name similiarity with gear names
      for (Gear gear : gears) {
        if (gear.getName().equals(name)) {
          try {
            SstPersistence.save();
          } catch (RuntimeException e) {
            System.out.println("error");
          }
          return "A piece of gear with the same name already exists"; // if it's a repeat name
        }
      }

      List<Combo> combos = sst.getCombos(); // gets the list of combo with variable combos (this
                                            // will not be redocumented in later methods to avoid
                                            // redundancy)
      // check name similarity with the combos name
      for (Combo combo : combos) {
        if (combo.getName().equals(name)) {
          try {
            SstPersistence.save();
          } catch (RuntimeException e) {
            System.out.println("error");
          }
          return "A combo with the same name already exists"; // if it's a repeat combo
        }
      }

      sst.addGear(sst.addGear(name, pricePerWeek)); // add a piece of gear sucessfully
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
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
    // get gear with corresponding name
    Gear aGear = null;
    List<Gear> gears = sst.getGear();
    for (Gear gear : gears) { // if the gear exists
      if (gear.getName().equals(name))
        aGear = gear;
    }
    if (aGear == null) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "gear with name: " + name + " ,doesn't exist";
    }

    // unsuccesfully delete a gear that is in an existing combo
    List<Combo> combos = sst.getCombos();
    for (Combo combo : combos) {
      List<ComboItem> items = combo.getComboItems();
      for (ComboItem item : items) { // if there is the piece of gear in the combo, it cannot be
                                     // deleted
        if (item.getGear().getName().equals(name)) {
          try {
            SstPersistence.save();
          } catch (RuntimeException e) {
            System.out.println("error");
          }
          return "The piece of gear is in a combo and cannot be deleted";
        }
      }
    }

    // successfully delete a piece of gear
    aGear.delete();
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      System.out.println("error");
    }
    return "";
  }

  /**
   * @author Yassine Mimet adds a combo
   * @param name
   * @param discount
   * @return error message
   */
  public static String addCombo(String name, int discount) {
    if (discount < 0) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "Discount must be at least 0";
    }
    // the if statements above and below this comment check 0 < discount < 100
    if (discount > 100) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "Discount must be no more than 100";
    }

    if (name == null || name.isBlank()) { // checks for validity
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The name must not be empty ";
    }
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

    List<Combo> combos = sst.getCombos();
    for (Combo combo : combos) { // if the combo names is not unique
      if (combo.getName().equals(name)) {
        try {
          SstPersistence.save();
        } catch (RuntimeException e) {
          System.out.println("error");
        }
        return "A combo with the same name already exists";
      }
    }

    List<Gear> gears = sst.getGear();
    for (Gear gear : gears) { // if a piece of gear already exists with the same name
      if (gear.getName().equals(name)) {
        try {
          SstPersistence.save();
        } catch (RuntimeException e) {
          System.out.println("error");
        }
        return "A piece of gear with the same name already exists";
      }
    }

    sst.addCombo(sst.addCombo(name, discount)); // successfully adds the combo with the discount
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      System.out.println("error");
    }
    return "";
  }

  /**
   * @author Yassine Mimet deletes combo
   * @param name
   */
  public static void deleteCombo(String name) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();

    Combo aCombo = null;
    List<Combo> combos = sst.getCombos();
    for (Combo combo : combos) { // if the combo matches the same name as another combo
      if (combo.getName().equals(name))
        aCombo = combo;
    }
    if (aCombo == null) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return;
    }

    if (aCombo != null) {
      aCombo.delete(); // if the combo is null, successfully deletes the combo
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
    }
  }

  // this method does not need to be implemented by a team with five team members
  /**
   * @author Martin Eskaros adds gear to Combo
   * @param gearName
   * @param comboName
   * @return error message
   */
  public static String addGearToCombo(String gearName, String comboName) {

    if (gearName == null || gearName.isBlank()) { // if the gear name is empty
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The gear name must not be empty ";
    }
    if (comboName == null || comboName.isBlank()) { // if the combo name is empty
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The comboName name must not be empty ";
    }

    boolean comboExists = false; // initializing variables
    boolean gearExists = false;
    ComboItem gearComboItem = null;
    Combo tCombo = null;
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Combo> allCombos = sst.getCombos(); // all combos in the Snow Shoe Tour
    for (Combo combo : allCombos) { // iterate through all combos + check if combo exists
      if (combo.getName().equals(comboName)) { // if combo exists, tCombo to combo and set
                                               // comboExists to true
        tCombo = combo;
        comboExists = true;
        break;
      }
    }
    // Combo doesnt exist
    if (!comboExists) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The combo does not exist";
    }

    List<Gear> gears = sst.getGear(); // get all gears in the Snow Shoe Tour
    Gear tGear = null;
    for (Gear gear : gears) { // check if gear exists
      if (gear.getName().equals(gearName)) {
        tGear = gear;
        gearExists = true;
        break;
      }
    }
    // Gear doesnt exist
    if (!gearExists) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The piece of gear does not exist";
    }

    // if gear already is in the combo, then just increment the quantity
    for (ComboItem item : tCombo.getComboItems()) {
      if (item.getGear().getName().equals(tGear.getName())) {
        int q = item.getQuantity();
        q++;
        item.setQuantity(q);
        try {
          SstPersistence.save();
        } catch (RuntimeException e) {
          System.out.println("error");
        }
        System.out.println("reached from controller");
        return "";
      }
    }
    System.out.println("reached from controller");
    sst.addComboItem(1, tCombo, tGear);
    /*gearComboItem = new ComboItem(1, sst, tCombo, tGear); // if gear exists, then we want it to be a
                                                          // comboItem so we can add it to our
                                                          // combo.

    // Add combo item (gear) to the combo.
    tCombo.addComboItem(gearComboItem);
    // Add combo item to the list of combo items in the gear
    tGear.addComboItem(gearComboItem);*/
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      System.out.println("error");
    }

    return "";
  }

  /**
   * @author Wasif Somji removes a Gear piece from Combo
   * @param gearName
   * @param comboName
   * @return error message
   */
  public static String removeGearFromCombo(String gearName, String comboName) {
    // TODO Implement the method, return error message (if any)
    if (gearName == null || gearName.isBlank()) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The gear name must not be empty ";
    }
    if (comboName == null || comboName.isBlank()) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The comboName name must not be empty ";
    }

    boolean comboExists = false;
    boolean gearExists = false;
    Combo tCombo = null;
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Combo> allCombos = sst.getCombos(); // all combos in the Snow Shoe Tour
    for (Combo combo : allCombos) { // iterate through all combos +check if combo exists
      if (combo.getName().equals(comboName)) {
        tCombo = combo;
        comboExists = true;
        break;
      }
    }
    // Combo doesnt exist
    if (!comboExists) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The combo does not exist";
    }

    List<Gear> gears = sst.getGear(); // get all gears in the Snow Shoe Tour
    Gear tGear = null;
    for (Gear gear : gears) { // check if gear exists
      if (gear.getName().equals(gearName)) {
        tGear = gear;
        gearExists = true;
        break;
      }
    }
    // Gear doesnt exist
    if (!gearExists) {
      try {
        SstPersistence.save();
      } catch (RuntimeException e) {
        System.out.println("error");
      }
      return "The piece of gear does not exist";
    }
    for (ComboItem comboItem : tCombo.getComboItems()) { // iterate over combo items in desired
                                                         // combo
      if (comboItem.getGear() == tGear) { // if the combo item is of the associated gear, remove it
                                          // from the combo.
        comboItem.setQuantity(comboItem.getQuantity() - 1);
        //TODO remove
        System.out.println("decremented quantity in controller");
        System.out.println(comboItem.getQuantity()+" for "+comboItem.getGear().getName()+" in "+comboItem.getCombo().getName());
        if (comboItem.getQuantity() < 1) { //if quantity zero , has to be removed
          if (tCombo.getComboItems().size() <= 2) { // if the combo only has one piece of gear
            comboItem.setQuantity(comboItem.getQuantity() + 1);
            try {
              SstPersistence.save();
            } catch (RuntimeException e) {
              System.out.println("error");
            }
            return "A combo must have at least two pieces of gear";
          }
          comboItem.delete(); // delete the combo item
          System.out.println("deleted in controller");
          try {
            SstPersistence.save();
          } catch (RuntimeException e) {
            System.out.println("error");
          }
        }
        return "";
      }
    }
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      System.out.println("error");
    }
    return "";
  }
}
