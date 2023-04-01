package ca.mcgill.ecse.snowshoetours.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.controller.SnowShoeTourStateMachineController;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
import ca.mcgill.ecse.snowshoetours.model.Tour;
import ca.mcgill.ecse.snowshoetours.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ToursStepDefinitions {
  private SnowShoeTour sst;
  private String error;

  /**
   * @author Yassine Mimet
   *
   * @param dataTable
   */
  @Given("the following SnowShoeTours system exists")
  public void the_following_snow_shoe_tours_system_exists(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      Date startDate = Date.valueOf(row.get("startDate")); // Extract data from the Cucumber data table                                                         
      int nrWeeks = Integer.parseInt(row.get("nrWeeks")); // gets number of weeks
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek")); //gets price per guide
      sst = SnowShoeToursApplication.getSnowShoeTour(); // Instantiate sst
      sst.setStartDate(startDate); // Setters
      sst.setNrWeeks(nrWeeks);
      sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

      error = ""; // error counter
    }
  }

  /**
   * @author Yassine Mimet
   * 
   * @param string
   */
  @Given("the participant with email {string} has started their tour")
  public void the_participant_with_email_has_started_their_tour(String string) {
    List<Participant> participants = sst.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(string)) {
        participant.pay("Paid"); // participant pays for their tour
        participant.startTrip(participant.getWeekAvailableFrom()); //participant starts trip from the week they're available
        break;
      }
    }
  }

  /**
   * @author Yassine Mimet
   * 
   * @param string
   */
  @Given("the participant with email {string} has paid for their tour")
  public void the_participant_with_email_has_paid_for_their_tour(String string) {
    List<Participant> participants = sst.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(string)) {
        participant.pay("Paid"); // participant pays for their tour
        break;
      }
    }
  }

  /**
   * @author Yassine Mimet
   * 
   */
  @Given("the following guides exist in the system")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var guide : rows) {
      sst.addGuide(guide.get("email"), guide.get("password"), guide.get("name"),
          guide.get("emergencyContact"));
    }
  }

  /**
   * @author Yassine Mimet
   * 
   * @param string
   */
  @Given("the participant with email {string} has cancelled their tour")
  public void the_participant_with_email_has_cancelled_their_tour(String string) {
    List<Participant> participants = sst.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(string)) {
        participant.cancel(); // participant cancels their tour
        break;
      }
    }
  }

  /**
   * @author Yassine Mimet
   * @param dataTable
   */
  @Given("the following snowshoe tours exist in the system")
  public void the_following_snowshoe_tours_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var tour : rows) {
      Tour tourToAssign = sst.addTour(Integer.parseInt(tour.get("id")),
          Integer.parseInt(tour.get("startWeek")), Integer.parseInt(tour.get("endWeek")),
          (Guide) User.getWithAccountName(tour.get("guide")));
      String participant = tour.get("participants");
      Participant aParticipant = (Participant) User.getWithAccountName(participant);
      tourToAssign.addParticipant(aParticipant);
      aParticipant.assign(tourToAssign);
    }
  }

  /**
   * @author Yassine Mimet
   * 
   * @param dataTable
   */
  @Given("the following participants exist in the system")
  public void the_following_participants_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var participant : rows) {
      sst.addParticipant(participant.get("email"), participant.get("password"),
          participant.get("name"), participant.get("emergencyContact"),
          Integer.parseInt(participant.get("nrWeeks")),
          Integer.parseInt(participant.get("weeksAvailableFrom")),
          Integer.parseInt(participant.get("weeksAvailableUntil")),
          Boolean.valueOf(participant.get("lodgeRequired")), null, 0);
    }
  }

  /**
   * @author souhail el hayani
   * @param string
   */
  @Given("the participant with email {string} has finished their tour")
  public void the_participant_with_email_has_finished_their_tour(String string) {
    List<Participant> par = sst.getParticipants();
    for (Participant participant : par) {
      if (participant.getAccountName().equals(string)) {
        participant.finishTrip();
        break;
      }
    }
  }

  /**
   * @author souhail el hayani
   * @param string
   */
  @When("the manager attempts to cancel the tour for email {string}")
  public void the_manager_attempts_to_cancel_the_tour_for_email(String string) {
    error = SnowShoeTourStateMachineController.cancelTrip(string);
  }

  /**
   * @author souhaill el hayani
   */
  @When("the administrator attempts to initiate the snowshoe tour creation process")
  public void the_administrator_attempts_to_initiate_the_snowshoe_tour_creation_process() {
    error = SnowShoeTourStateMachineController.initiate();
  }

  /**
   * @author Wasif Somji
   * 
   * @param string
   */
  @When("the manager attempts to finish the tour for the participant with email {string}")
  public void the_manager_attempts_to_finish_the_tour_for_the_participant_with_email(
      String string) {
    error = SnowShoeTourStateMachineController.finishTour(string);
  }

  /**
   * @author Wasif Somji
   * 
   * @param string
   */
  @When("the manager attempts to start the tours for week {string}")
  public void the_manager_attempts_to_start_the_tours_for_week(String string) {
    error = SnowShoeTourStateMachineController.startTourForWeek(Integer.parseInt(string));
  }

  /**
   * @author Wasif Somji
   * 
   * @param string
   */
  @When("the manager attempts to confirm payment for email {string} using authorization code {string}")
  public void the_manager_attempts_to_confirm_payment_for_email_using_authorization_code(
      String string, String string2) {
    error = SnowShoeTourStateMachineController.confirmPayement(string, string2);
  }

  /**
   * @author souhail el hayani
   * @param dataTable
   */
  @Then("the following snowshoe tours shall exist in the system with a guide")
  public void the_following_snowshoe_tours_shall_exist_in_the_system_with_a_guide(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    List<Tour> tours = sst.getTours();
    for (var row : rows) {
      int id = Integer.parseInt(row.get("id")); // get the id of the tour we are looking for
      Tour found = null;
      for (Tour tour : tours) {
        if (tour.getId() == id) { // get tour with that specific id
          found = tour;
          break;
        }
      }
      assertNotNull(found); // check if tour exists with that id

      assertNotNull(found.getGuide()); // check if a guide is assigned to it
    }
  }

  /**
   * @author Hamza Alfarrash
   * 
   * @param string
   * @param string2
   */
  @Then("the participant with email {string} shall be marked as {string}")
  public void the_participant_with_email_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    Participant participant = (Participant) User.getWithAccountName(string);
    assertEquals(string2, participant.getStatusFullName());
  }

  /**
   * @author Hamza Alfarrash
   * 
   * @param string
   */
  @Then("the number of snowshoe tours shall be {string}")
  public void the_number_of_snowshoe_tours_shall_be(String string) {
    assertEquals(string, String.valueOf(sst.getTours().size()));
  }

  /**
   * @author Hamza Alfarrash
   * 
   * @param string
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    assertEquals(error, string);
  }

  /**
   * @author Hamza Alfarrash Checks that the participant does not exist
   * @param string email of the participant
   */
  @Then("a participant account shall not exist with email {string}")
  public void a_participant_account_shall_not_exist_with_email(String string) {

    User participant = User.getWithAccountName(string);
    Boolean participantExist = false;
    if (participant instanceof Participant) {
      participantExist = true;
    }
    assertFalse(participantExist);
  }

  /**
   * @author Hamza Alfarrash Check the number of participants
   * @param string
   */
  @Then("the number of participants shall be {string}")
  public void the_number_of_participants_shall_be(String string) {
    assertEquals(string, String.valueOf(sst.getParticipants().size()));
  }

  /**
   * @author Hamza Alfarrash Checks that the participant exist and have the right refund percentage
   * @param string
   * @param string2
   */
  @Then("a participant account shall exist with email {string} and a refund of {string} percent")
  public void a_participant_account_shall_exist_with_email_and_a_refund_of_percent(String string,
      String string2) {
    Boolean check = null;
    Participant aParticipant = null;
    List<Participant> participants = sst.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(string)) {
        aParticipant = participant;
        check = true;
      }
    }
    assertTrue(check);
    assertEquals(string2, String.valueOf(aParticipant.getRefundedPercentageAmount()));
  }

  /**
   * @author souhail el hayani
   * @param string
   * @param string2
   */
  @Then("a participant account shall exist with email {string} and authorization code {string}")
  public void a_participant_account_shall_exist_with_email_and_authorization_code(String string,
      String string2) {
    Boolean check = null;
    Participant aParticipant = null;
    List<Participant> participants = sst.getParticipants();
    for (Participant participant : participants) {
      if (participant.getAccountName().equals(string)) { // get corresponding participant
        aParticipant = participant;
        check = true;
      }
    }
    assertTrue(check); // if false then it doesnt exist
    assertEquals(string2, String.valueOf(aParticipant.getAuthorizationCode())); // compare the
                                                                                // authorization
                                                                                // code
  }
}
