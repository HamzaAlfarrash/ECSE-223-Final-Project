package ca.mcgill.ecse.snowshoetours.controller;

/**
 * 
 * @author souhail el hayani
 *
 */
public class TOGears {
  
  private int pricePerWeek;
  private String name;
  
  public TOGears(int p, String n) {
    pricePerWeek = p;
    name = n;
  }
  
  public String getName() {
    return name;
  }

}
