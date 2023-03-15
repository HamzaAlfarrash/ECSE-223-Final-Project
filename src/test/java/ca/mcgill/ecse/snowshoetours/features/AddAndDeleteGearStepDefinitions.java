package ca.mcgill.ecse.snowshoetours.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.ComboItem;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.controller.GearController;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;

public class AddAndDeleteGearStepDefinitions {

  private SnowShoeTour sst;
  private String error;
  
  /**
   * 
   * @param dataTable
   * 
   * Yassine Mimet
   */
  @Given("the following SnowShoeTour system exists \\(g5)")
  public void the_following_snow_shoe_tour_system_exists_g5(
      io.cucumber.datatable.DataTable dataTable) {
    sst = SnowShoeToursApplication.getSnowShoeTour(); 
    List<Map<String, String>> rows = dataTable.asMaps();
    for(var row : rows) {
      Date startDate = Date.valueOf(row.get("startDate"));
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
      sst.setStartDate(startDate);
      sst.setNrWeeks(nrWeeks);
      sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    }
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param dataTable
   */
  @Given("the following pieces of gear exist in the system \\(g5)")
  public void the_following_pieces_of_gear_exist_in_the_system_g5(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String name = row.get("name");
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek")); 
      sst.addGear(name, pricePerWeek); //add gear to the application, check if null in @then clause ! 
    }
 
  }

  @Given("the following combos exist in the system \\(g5)")
  public void the_following_combos_exist_in_the_system_g5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  
  /**
   * @author Yassine Mimet
   * 
   * @param string
   */
  @When("the manager attempts to delete the piece of gear with name {string} \\(g5)")
  public void the_manager_attempts_to_delete_the_piece_of_gear_with_name_g5(String string) {
    error = GearController.deleteGear(string);
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param string name
   * @param string2 price per week
   */
  @When("the manager attempts to add a new piece of gear with name {string} and price per week {string} \\(g5)")
  public void the_manager_attempts_to_add_a_new_piece_of_gear_with_name_and_price_per_week_g5(
      String string, String string2) {
    error = GearController.addGear(string, Integer.parseInt(string2));
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param string - gear name
   * @param string2 - price per week
   */
  @Then("a piece of gear shall exist with name {string} and price per week {string} \\(g5)")
  public void a_piece_of_gear_shall_exist_with_name_and_price_per_week_g5(String string,
      String string2) {
    boolean flag = false;
    List<Gear> list = sst.getGear(); //get the list of gears
    for(Gear gear : list) {
      if(gear.getName().equals(string) && gear.getPricePerWeek()==Integer.parseInt(string2)) {
        flag=true; //once found, set flag to true and break out of the loop
        break;
      }
    }
    assertTrue(flag);
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param string - gear name
   * @param string2 - price per week
   */
  @Then("a piece of gear shall not exist with name {string} and price per week {string} \\(g5)")
  public void a_piece_of_gear_shall_not_exist_with_name_and_price_per_week_g5(String string,
      String string2) {
    boolean flag = false;
    List<Gear> list = sst.getGear(); //get the list of gears
    for(Gear gear : list) {
      if(gear.getName().equals(string) && gear.getPricePerWeek()==Integer.parseInt(string2)) {
        flag=true; //once found, set flag to true and break out of the loop
        break;
      }
    }
    assertFalse(flag); //similar to previous method, but flag has to be false
  }

  /**
   * @author Yassine Mimet
   * 
   * @param string
   */
  @Then("the number of pieces of gear in the system shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_in_the_system_shall_be_g5(String string) {
    int numberOfGear = sst.numberOfGear();
    assertEquals(string, Integer.toString(numberOfGear));
  }

  /**
   * @author Yassine Mimet
   * 
   * @param string
   */
  @Then("the number of pieces of gear shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_shall_be_g5(String string) {
    int numberOfGear = sst.numberOfGear();
    assertEquals(string, Integer.toString(numberOfGear));
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param string - the combo name
   * @param string2 - the gear name
   * @param string3 - the quantity of the gear in the combo
   */
  @Then("the combo with name {string} shall have a piece of gear with name {string} and quantity {string} \\(g5)")
  public void the_combo_with_name_shall_have_a_piece_of_gear_with_name_and_quantity_g5(
      String string, String string2, String string3) {
    List<Combo> comboList = sst.getCombos();
    Combo specifiedCombo = null;
    //get the combo with specified name
    for(Combo combo : comboList) {
      if(combo.getName().equals(string)) {
        specifiedCombo = combo;
        break;
      }
    }
    assertFalse(specifiedCombo == null); //throw exception if combo doesn't exist
    
    List<ComboItem> itemList = specifiedCombo.getComboItems(); //fetch all the combo items
    boolean flag = false;
    //search for the gear with the same quantity
    for(ComboItem item : itemList) {
      //get the corresponding item, that is the same gear with the same quantity
      if(item.getGear().getName().equals(string2) && item.getQuantity()==Integer.parseInt(string3)) {
        flag = true;
        break;
      }
    }
    assertTrue(flag); //if false, means not found, throw exception
  }

  @Then("the number of pieces of gear for the combo with name {string} shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_for_the_combo_with_name_shall_be_g5(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param string -  the error message
   */
  @Then("the system shall raise the error {string} \\(g5)")
  public void the_system_shall_raise_the_error_g5(String string) {
    assertEquals(error, "The piece of gear is in a combo and cannot be deleted");
  }

  @Given("the following participants exist in the system \\(g5)")
  public void the_following_participants_exist_in_the_system_g5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  /**
   * @author Souhail El Hayani
   * 
   * @param dataTable
   */
  @Given("the following participants request the following pieces of gear \\(g5)")
  public void the_following_participants_request_the_following_pieces_of_gear_g5(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    List<Map<String, String>> rows = dataTable.asMaps();
    List<Gear> gears = sst.getGear(); //get all gears in sst
    for(var row: rows) {
      String personName = row.get("email");
      String gearName = row.get("gear");
      int quantity = Integer.parseInt("quantity");
      List<Participant> participants = sst.getParticipants();
      Participant foundParticipant = null;
      Gear correspondingGear = null;
      //get the participant with the corresponding name
      for(Participant par : participants) {
        if(par.getAccountName().equals(personName)) {
          foundParticipant = par; //this is not null since previous Given clause checks for that
          break;
        }
      }
      //add gear to participant
      //check if gear with that name already exists, previous @Given clase checks for that
      for(Gear gear : gears) {
        if(gear.getName().equals(gearName)) {
          correspondingGear = gear;
          break;
        }
      }
      BookedItem item = new BookedItem(quantity,sst, foundParticipant, correspondingGear);
      foundParticipant.addBookedItem(item);
    }
  }

  @Then("a piece of gear shall not exist with name {string} \\(g5)")
  public void a_piece_of_gear_shall_not_exist_with_name_g5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the participant with email {string} shall have a piece of gear with name {string} and quantity {string} \\(g5)")
  public void the_participant_with_email_shall_have_a_piece_of_gear_with_name_and_quantity_g5(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of gear for the participant with email {string} shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_for_the_participant_with_email_shall_be_g5(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
