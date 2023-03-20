package ca.mcgill.ecse.snowshoetours.controller;

import java.sql.Date;
import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class SnowShoeTourController {
  public static TOSnowShoeTour getSnowShoeTour(int id) {
    // TODO Implement the method
	for (Tour tour: tours) {
		if (tour.getId == id) {
			TOSnowShoeTour TOtour = new TOSnowShoeTour();
			TOtour.id = id;
			TOtour.startWeek = tour.getStartWeek();
			TOtour.endWeek = tour.getEndWeek();
			TOtour.guideEmail = guide.getGuideEmail();
			TOtour.guideName = guide.getGuideName();
			TOtour.totalCostForGuide = guide.getTotalCostForGuide();
			return TOtour;
		}
	}
    return null;
  }

  public static String updateSnowShoeTour(Date startDate, int nrWeeks, int priceOfGuidePerWeek) {
    // TODO Implement the method, return error message (if any)
    SnowShoeTour sst = SnowShoeToursApplication.getSnowShoeTour();
    
    //input validation
    if(nrWeeks < 0) return "The number of riding weeks must be greater than or equal to zero";
    if(priceOfGuidePerWeek<0) return "The price of guide per week must be greater than or equal to zero";
    if(startDate.getYear()<sst.getStartDate().getYear()) return "The start date cannot be from previous year or earlier";
    
    sst.setStartDate(startDate);
    sst.setNrWeeks(nrWeeks);
    sst.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
    
    return "";
  }
}
