package ca.mcgill.ecse.snowshoetours.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import java.sql.Date;

public class AddAndDeleteGearStepDefinitions {

  private SnowShoeTour sst;
  
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

  @When("the manager attempts to delete the piece of gear with name {string} \\(g5)")
  public void the_manager_attempts_to_delete_the_piece_of_gear_with_name_g5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to add a new piece of gear with name {string} and price per week {string} \\(g5)")
  public void the_manager_attempts_to_add_a_new_piece_of_gear_with_name_and_price_per_week_g5(
      String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("a piece of gear shall exist with name {string} and price per week {string} \\(g5)")
  public void a_piece_of_gear_shall_exist_with_name_and_price_per_week_g5(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("a piece of gear shall not exist with name {string} and price per week {string} \\(g5)")
  public void a_piece_of_gear_shall_not_exist_with_name_and_price_per_week_g5(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of gear in the system shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_in_the_system_shall_be_g5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of gear shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_shall_be_g5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the combo with name {string} shall have a piece of gear with name {string} and quantity {string} \\(g5)")
  public void the_combo_with_name_shall_have_a_piece_of_gear_with_name_and_quantity_g5(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of gear for the combo with name {string} shall be {string} \\(g5)")
  public void the_number_of_pieces_of_gear_for_the_combo_with_name_shall_be_g5(String string,
      String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string} \\(g5)")
  public void the_system_shall_raise_the_error_g5(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
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
    throw new io.cucumber.java.PendingException();
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
