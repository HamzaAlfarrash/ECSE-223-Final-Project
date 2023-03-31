package ca.mcgill.ecse.snowshoetours.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.Guide;
import ca.mcgill.ecse.snowshoetours.model.Participant;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;
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
    public void the_following_snow_shoe_tours_system_exists(io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> rows = dataTable.asMaps();
      for (var row : rows) {
        Date startDate = Date.valueOf(row.get("startDate")); // Extract data from the Cucumber data
                                                             // table
        int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
        int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));
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
          participant.startTrip(participant.getWeekAvailableFrom());
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
            participant.pay("Paid");
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
       sst.addGuide(guide.get("email"), guide.get("password"), guide.get("name"), guide.get("emergencyContact"));
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
      for(Participant participant : participants ) {
        if(participant.getAccountName().equals(string)) {
          participant.cancel();
          break;
        }
      }
    }

    /**
     * @author Yassine Mimet
     * @param dataTable
     */
    @Given("the following snowshoe tours exist in the system")
    public void the_following_snowshoe_tours_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> rows = dataTable.asMaps();
      for (var tour : rows) {
        sst.addTour(Integer.parseInt(tour.get("id")), Integer.parseInt(tour.get("startWeek")), Integer.parseInt(tour.get("endWeek")), (Guide) User.getWithAccountName(tour.get("guide")));
      }
    }

    /**
     * @author Yassine Mimet
     * 
     * @param dataTable
     */
    @Given("the following participants exist in the system")
    public void the_following_participants_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
      List<Map<String, String>> rows = dataTable.asMaps();
      for (var participant : rows) {
       sst.addParticipant(participant.get("email"), participant.get("password"), participant.get("name"), participant.get("emergencyContact"), Integer.parseInt(participant.get("nrWeeks")), Integer.parseInt(participant.get("weeksAvailableFrom")), Integer.parseInt(participant.get("weeksAvailableUntil")), Boolean.valueOf(participant.get("lodgeRequired")), null, 0);
      }
    }
    
    
    @Given("the participant with email {string} has finished their tour")
    public void the_participant_with_email_has_finished_their_tour(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the manager attempts to cancel the tour for email {string}")
    public void the_manager_attempts_to_cancel_the_tour_for_email(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the administrator attempts to initiate the snowshoe tour creation process")
    public void the_administrator_attempts_to_initiate_the_snowshoe_tour_creation_process() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     /**
     * @author Wasif Somji
     * 
     * @param string
     */
    @When("the manager attempts to finish the tour for the participant with email {string}")
    public void the_manager_attempts_to_finish_the_tour_for_the_participant_with_email(String string) {
        for (Participant participant : participants) {
          if participant.getAccountName().equals(email)) {
            participant.finishTour();
            break;
          }
      
        }
            
    }
  
    /**
     * @author Wasif Somji
     * 
     * @param string
     */
    @When("the manager attempts to start the tours for week {string}")
    public void the_manager_attempts_to_start_the_tours_for_week(String string) {
        for (Tour tour: tours) {
          if (tour.getWeek().equals(string)) {
            tour.start()
              break;
          }
       }
    }
    /**
     * @author Wasif Somji
     * 
     * @param string
     */
    @When("the manager attempts to confirm payment for email {string} using authorization code {string}")
    public void the_manager_attempts_to_confirm_payment_for_email_using_authorization_code(String string, String string2) {
        // to do 
    }
    
    
    @Then("the following snowshoe tours shall exist in the system with a guide")
    public void the_following_snowshoe_tours_shall_exist_in_the_system_with_a_guide(io.cucumber.datatable.DataTable dataTable) {
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
     * @author Hamza Alfarrash
     * 
     * @param string
     * @param string2
     */
    @Then("the participant with email {string} shall be marked as {string}")
    public void the_participant_with_email_shall_be_marked_as(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
    	Participant participant = (Participant) User.getWithEmail(string);
        assertEquals(string2, participant.getParticipantStatusFullName());
    }
    
    /**
     * @author Hamza Alfarrash
     * 
     * @param string
     */
    @Then("the number of snowshoe tours shall be {string}")
    public void the_number_of_snowshoe_tours_shall_be(String string) {
    	assertEquals(string,String.valueOf(SnowShoeTour.numberOfTours()));
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
     * @author Hamza Alfarrash
     * Checks that the participant does not exist
     * @param string email of the participant
     */
    @Then("a participant account shall not exist with email {string}")
    public void a_participant_account_shall_not_exist_with_email(String string) {

        User participant = User.getWithEmail(string);
        Boolean participantExist = false;
        if(participant instanceof Participant){
          participantExist = true;
        }
        assertFalse(participantExist);
    }
    /**
     * @author Hamza Alfarrash
     * Check the number of participants
     * @param string
     */
    @Then("the number of participants shall be {string}")
    public void the_number_of_participants_shall_be(String string) {
    	assertEquals(string, String.valueOf(SnowShoeTour.numberOfParticipants()));
    }
    /**
     * @author Hamza Alfarrash
     * Checks that the participant exist and have the right refund percentage
     * @param string
     * @param string2
     */
    @Then("a participant account shall exist with email {string} and a refund of {string} percent")
    public void a_participant_account_shall_exist_with_email_and_a_refund_of_percent(String string, String string2) {

        User user = User.getWithEmail(string);
        boolean existingParticipant = false;
        int refund = 0;
        int refundExpected = Integer.valueOf(string2);

        if (user instanceof Participant){
          existingParticipant = true;
          Participant participant = (Participant)user;
        }

        assertTrue(existingParticipant);
        refund = participant.getRefundedPercentageAmount();
        assertEquals(refundExpected,refund);
    }

    @Then("a participant account shall exist with email {string} and authorization code {string}")
    public void a_participant_account_shall_exist_with_email_and_authorization_code(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
