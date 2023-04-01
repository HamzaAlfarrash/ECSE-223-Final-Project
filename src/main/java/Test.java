import ca.mcgill.ecse.snowshoetours.model.*;
import ca.mcgill.ecse.snowshoetours.application.*;
import ca.mcgill.ecse.snowshoetours.controller.*;
import ca.mcgill.ecse.snowshoetours.persistence.*;
public class Test {

  public static void main(String[] args) {
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    SstPersistence.load();
    sst.addParticipant("name", "pass", "name", "123", 3, 1, 3, false, "pay", 20);
    
    try {
      SstPersistence.save();
    } catch (RuntimeException e) {
      System.out.println("error");
    }
    //SnowShoeTourStateMachineController.initiate();
    //ParticipantController.registerParticipant("name", "abc", "abc", "123", 3, 1, 3, false);
    //ParticipantController.registerParticipant("name", "abc", "abc", "123", 3, 1, 3, false);
    System.out.println("success");
  }
}
