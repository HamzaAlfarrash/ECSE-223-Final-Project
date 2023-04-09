import ca.mcgill.ecse.snowshoetours.model.*;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.snowshoetours.application.*;
import ca.mcgill.ecse.snowshoetours.controller.*;
import ca.mcgill.ecse.snowshoetours.persistence.*;
public class Test {

  public static void main(String[] args) {
    
    List<TOGears> list = GearController.getGears();
    List<String> names = new ArrayList<>();
    for (TOGears gear : list) {
      names.add(gear.getName());
    }
    System.out.println(names.size());
    System.out.println(names.get(0));
    System.out.println(names.get(1));
    System.out.println(names.get(2));
    
    /*SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    List<Guide> guides = sst.getGuides();
    Manager manager = sst.getManager();
    List<Participant> participants = sst.getParticipants();

    //User.reinitializeUniqueAccountName(manager, guides, participants);

    try {
      Participant p1 = sst.addParticipant("name", "pass", "name", "123", 3, 1, 3, false, "pay", 20);
      Participant p2 = sst.addParticipant("name1", "pass", "name", "123", 3, 2, 4, false, "pay", 20);
      Participant p3 = sst.addParticipant("name2", "pass", "name", "123", 3, 3, 5, false, "pay", 20);
      Participant p4 = sst.addParticipant("name3", "pass", "name", "123", 3, 4, 6, false, "pay", 20);
      Participant p5 = sst.addParticipant("name4", "pass", "name", "123", 3, 5, 7, false, "pay", 20);
      //sst.removeParticipant((Participant)Participant.getWithAccountName("name"));
      System.out.print("list of participants: \n");
      List<Participant> list = sst.getParticipants();
      for(Participant par : list) {
        System.out.print(par+",\n");
      }
      
      System.out.println("participant creation success");
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    
    try {
      sst.addGuide("aguide", "hello", "lol", "lbazaglo");
      sst.addGuide("aguide1", "hello", "lol", "lbazaglo");
      sst.addGuide("aguide2", "hello", "lol", "lbazaglo");
      System.out.print("list of guides: \n");
      List<Guide> list2 = sst.getGuides();
      for(Guide guide : list2) {
        System.out.print(guide+",\n");
      }
      
      SnowShoeTourStateMachineController.initiate();
      System.out.print("list of tours: \n");
      List<Tour> list = sst.getTours();
      for(Tour tour : list) {
        System.out.print(tour+",\n");
      }
      
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }
    
    try {
      Gear g1 = sst.addGear("gloves", 5);
      Gear g2 = sst.addGear("boots", 15);
      Gear g3 = sst.addGear("goggles", 25);
      List<Participant> list = sst.getParticipants();
      for(Participant par : list) {
        ParticipantController.addBookableItemToParticipant(par.getAccountName(), g1.getName());
      }
      System.out.println("added gear successfully");
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }*/
    
  }
}
