package ca.mcgill.ecse.snowshoetours.persistence;

import ca.mcgill.ecse.snowshoetours.application.SnowShoeToursApplication;
import ca.mcgill.ecse.snowshoetours.model.SnowShoeTour;

public class SstPersistence {

	  private static String filename = "data.json";
	  private static JsonSerializer serializer = new JsonSerializer("ca.mcgill.ecse.snowshoetours");

	  public static void setFilename(String filename) {
	    SstPersistence.filename = filename;
	  }

	  public static void save() {
	    save(SnowShoeToursApplication.getSnowShoeTour());
	  }

	  public static void save(SnowShoeTour sst) {
	    serializer.serialize(sst, filename);
	  }

	  public static SnowShoeTour load() {
	    var sst = (SnowShoeTour) serializer.deserialize(filename);
	    // model cannot be loaded - create empty BTMS
	    if (sst == null) {
	      sst = new SnowShoeTour(null,0,0); //TODO what are the input of the constructor??
	    } else {
	      sst.reinitialize();
	    }
	    return sst;
	  }

}
