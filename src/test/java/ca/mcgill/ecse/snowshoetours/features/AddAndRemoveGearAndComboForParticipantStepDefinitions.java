
package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.ParticipantController;
import ca.mcgill.ecse.snowshoetours.model.*;
import ca.mcgill.ecse.snowshoetours.model.BookedItem;
import ca.mcgill.ecse.snowshoetours.model.Combo;
import ca.mcgill.ecse.snowshoetours.model.Gear;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddAndRemoveGearAndComboForParticipantStepDefinitions {
  private SnowShoeTour sst;
  private String error;

  /**
   *
   * @Description: the code to set up sst
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following SnowShoeTour system exists \\(g7)")
  public void the_following_snow_shoe_tour_system_exists_g7(io.cucumber.datatable.DataTable dataTable) {
    sst = SnowShoeToursApplication.getSnowShoeTour(); // Instantiate sst
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String startDate = row.get("startDate"); // Getting the starting date
      int nrWeeks = Integer.parseInt(row.get("nrWeeks")); // Getting the amount of week available
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek")); // Getting the price of the guide per
                                                                                  // week
      sst.setStartDate(Date.valueOf(startDate)); // Setting all the attributes into sst
      sst.setNrWeeks(nrWeeks);
      sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    }
  }

  /**
   *
   * @Description: the code to set up all the gears
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following pieces of gear exist in the system \\(g7)")
  public void the_following_pieces_of_gear_exist_in_the_system_g7(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String name = row.get("name"); // Getting the name of the gear
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek")); // Getting the price of the gear per week
      sst.addGear(name, pricePerWeek); // Creating the gear
    }

  }

  /**
   *
   * @Description: the code to set up all the combos
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following combos exist in the system \\(g7)")
  public void the_following_combos_exist_in_the_system_g7(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String name = row.get("name"); // Getting the name of combo
      int discount = Integer.parseInt(row.get("discount")); // Getting the discount for the gear
      String[] itemsString = row.get("items").split(","); // Getting the array of the gear names in the combo
      String[] quantityString = row.get("quantity").split(","); // Getting the array of the amount per gear in the combo
      Combo combo = sst.addCombo(name, discount); // Create the combo

      for (int i = 0; i < itemsString.length; i++) { // Iterating through the array of gear names
        String itemName = itemsString[i];
        for (Gear g : sst.getGear()) { // Iterating through all the possible gears in sst
          if (g.getName().equals(itemName)) { // If the name of the gear matches, add that gear into the combo
            combo.addComboItem(Integer.parseInt(quantityString[i]), sst, g);
            break;
          }
        }
      }
    }
  }

  /**
   *
   * @Description: the code to set up all the guides
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following guides exist in the system \\(g7)")
  public void the_following_guides_exist_in_the_system_g7(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String email = row.get("email"); // Getting the email of the guide
      String password = row.get("password"); // Getting the password of the guide
      String name = row.get("name"); // Getting the name of the guide
      String emergencyContact = row.get("emergencyContact"); // Getting the emergency contact of the guide
      sst.addGuide(email, password, name, emergencyContact); // Creating the guide instance
    }
  }

  /**
   *
   * @Description: the code to set up all the participants
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following participants exist in the system \\(g7)")
  public void the_following_participants_exist_in_the_system_g7(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String email = row.get("email"); // Getting the email of the participant
      String password = row.get("password"); // Getting the password of the participant
      String name = row.get("name"); // Getting the name of the participant
      String emergencyContact = row.get("emergencyContact"); // Getting the emergency contact of the participant
      int nrWeeks = Integer.parseInt(row.get("nrWeeks")); // Getting the amount of weeks available of the participant
      int weeksAvailableFrom = Integer.parseInt(row.get("weeksAvailableFrom")); // Getting the start week of the
                                                                                // participant
      int weeksAvailableUntil = Integer.parseInt(row.get("weeksAvailableUntil")); // Getting the end week of the
                                                                                  // participant
      boolean lodgeRequired = Boolean.parseBoolean(row.get("lodgeRequired")); // Store if the participant want a lodge
                                                                              // or not
      sst.addParticipant( // Creating the participant instance
          email,
          password,
          name,
          emergencyContact,
          nrWeeks,
          weeksAvailableFrom,
          weeksAvailableUntil,
          lodgeRequired,
          null,
          0);
    }
  }

  /**
   *
   * @Description: the code to set up all the gears the participants want
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following participants request the following pieces of gear \\(g7)")
  public void the_following_participants_request_the_following_pieces_of_gear_g7(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String email = row.get("email"); // Getting the email of the participant
      String gearName = row.get("gear"); // Getting the name of the gear
      int quantity = Integer.parseInt(row.get("quantity")); // Getting the amount of gear a participant want
      for (Participant p : sst.getParticipants()) { // Iterate through all the participants in sst
        if (p.getAccountName().equals(email)) { // If the emails match...
          for (Gear g : sst.getGear()) { // Iterate through all the gears in sst
            if (g.getName().equals(gearName)) { // If the names match, create a BookedItem for the participant
              p.addBookedItem(quantity, sst, g);
              break;
            }
          }
          break;
        }
      }
    }
  }

  /**
   *
   * @Description: the code to set up all the combos each participant want
   *
   * @param dataTable the table generated by cucumber
   */
  @Given("the following participants request the following combos \\(g7)")
  public void the_following_participants_request_the_following_combos_g7(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) { // Iterating through each row
      String email = row.get("email"); // Getting the email of the participant
      String gearName = row.get("gear"); // Getting the name of the combo
      int quantity = Integer.parseInt(row.get("quantity")); // Getting the amount of combo a participant want
      for (Participant p : sst.getParticipants()) { // Iterate through all the participants in sst
        if (p.getAccountName().equals(email)) { // If the emails match...
          for (Combo c : sst.getCombos()) { // Iterate through all the combos in sst
            if (c.getName().equals(gearName)) { // If the names match, create a BookedItem for the participant
              p.addBookedItem(quantity, sst, c);
              break;
            }
          }
          break;
        }
      }
    }
  }

  /**
   * 
   *         Method for when adding bookable item to participant. Relies on
   *         controller method from Feature set 3
   *         Also uses call controller method seen in tutorial 6; which also reads
   *         for an error
   * @param bookableItemName
   * @param email
   */
  @When("the manager attempts to add a piece of gear or combo with name {string} to the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_add_a_piece_of_gear_or_combo_with_name_to_the_participant_with_email_g7(
      String bookableItemName, String email) {
    callController(ParticipantController.addBookableItemToParticipant(email, bookableItemName));
  }

  /**
   *         Method for removing bookable item from participant. Relies on
   *         controller method from Feature set 3
   *         Also uses call controller method seen in tutorial 6; which sets the
   *         error
   * @param bookableItemName
   * @param email
   */
  @When("the manager attempts to remove a piece of gear or combo with name {string} from the participant with email {string} \\(g7)")
  public void the_manager_attempts_to_remove_a_piece_of_gear_or_combo_with_name_from_the_participant_with_email_g7(
      String bookableItemName, String email) {
    callController(ParticipantController.removeBookableItemFromParticipant(email, bookableItemName));
  }

  /**
   *         Simple assertion of number of registered participants. Must parseInt
   *         the number of participants
   *         to make sure that the comparison is of same type.
   * @param numberOfParticipants
   */
  @Then("the number of participants shall be {string} \\(g7)")
  public void the_number_of_participants_shall_be_g7(String numberOfParticipants) {
    assertEquals(Integer.parseInt(numberOfParticipants), sst.getParticipants().size());
  }

  /**
   *         Method to check if right error message is being deployed. Similar to
   *         tutorial 6 example.
   * @param errorMsg
   */
  @Then("the system shall raise the error {string} \\(g7)")
  public void the_system_shall_raise_the_error_g7(String errorMsg) {
    assertTrue(error.contains(errorMsg));
  }

  /**
   *         Method that asserts quantity of booked items. Uses the getWithEmail
   *         method from the user class, which
   *         returns a participant from its email. Again parseInt is used integer
   *         to integer comparison
   * @param email
   * @param bookedItemQuantity
   */
  @Then("the number of pieces of gear or combos for the participant with email {string} shall be {string} \\(g7)")
  public void the_number_of_pieces_of_gear_or_combos_for_the_participant_with_email_shall_be_g7(String email,
      String bookedItemQuantity) {
    Participant emailPart = (Participant) User.getWithAccountName(email);
    if (emailPart == null) {
      throw new AssertionError("The participant isn't registered in the system");
    }
    assertEquals(Integer.parseInt(bookedItemQuantity), emailPart.getBookedItems().size());
  }

  /**
   *         Assertion that a given bookable item is not booked under a
   *         participant. Uses getWithEmail method
   *         from the user class to acquire the participant with that email.
   * @param bookableItemName
   * @param email
   */
  @Then("a piece of gear or combo shall not exist with name {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_not_exist_with_name_for_the_participant_with_email_g7(
      String bookableItemName, String email) {
    Participant emailPart = (Participant) User.getWithAccountName(email);
    if (emailPart == null) {
      throw new AssertionError("The participant isn't registered in the system");
    }
    for (BookedItem bI : emailPart.getBookedItems()) {
      assertNotEquals(bookableItemName, bI.getItem().getName());
    }
  }

  /**
   *         Assertion that a certain quantity of a bookableItem are booked under
   *         a given participant and that that bookableItem
   *         exists under that participant. Very similar to last method
   *         (
   *         a_piece_of_gear_or_combo_shall_not_exist_with_name_for_the_participant_with_email_g7).
   *         Additional complexity of
   *         if statement as assertion only works if participant has booked that
   *         bookable item.
   * @param bookableItemName
   * @param bItemQuantity
   * @param email
   */
  @Then("a piece of gear or combo shall exist with name {string} and quantity {string} for the participant with email {string} \\(g7)")
  public void a_piece_of_gear_or_combo_shall_exist_with_name_and_quantity_for_the_participant_with_email_g7(
      String bookableItemName, String bItemQuantity, String email) {
    Participant emailPart = (Participant) User.getWithAccountName(email);
    if (emailPart == null) {
      throw new AssertionError("The participant isn't registered in the system");
    }
    boolean itemFound = false;
    for (BookedItem bI : emailPart.getBookedItems()) {
      if (bI.getItem().getName().equals(bookableItemName)) {
        itemFound = true;
        assertEquals(bookableItemName, bI.getItem().getName());
        assertEquals(Integer.parseInt(bItemQuantity), bI.getQuantity());
      }
    }
    if (!itemFound) {
      throw new AssertionError("The item " + bookableItemName + " does not exist.");
    }
  }

  /**
   *         Calls controller and sets error and counter.
   * 
   */
  private void callController(String result) {
    if (result != null && !result.isEmpty()) {
      error += result;
    }
  }
}
