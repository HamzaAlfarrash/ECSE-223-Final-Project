package ca.mcgill.ecse.snowshoetours.application;

import java.sql.Date;
import ca.mcgill.ecse.snowshoetours.model.Manager;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class SnowShoeToursApplication {

  private static SnowShoeTour snowShoeTour;

  public static void main(String[] args) {
    snowShoeTour = null;
    
  }

  public static SnowShoeTour getSnowShoeTour() {
    if (snowShoeTour == null) {
      // these attributes are default, you should set them later with the setters
      snowShoeTour = new SnowShoeTour(new Date(0), 0, 0);
    }
    if ( snowShoeTour.getManager() == null ) {
      snowShoeTour.setManager(new Manager("manager", "manager", snowShoeTour));
    }
    return snowShoeTour;
  }
  
}