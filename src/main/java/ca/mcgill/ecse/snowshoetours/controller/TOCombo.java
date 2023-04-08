package ca.mcgill.ecse.snowshoetours.controller;

import java.util.List;

public class TOCombo {


  //Combo Attributes
  private int discount;
  private String name;
  private List<TOGears> comboItems;
  
  public TOCombo(String n, int d, List<TOGears> list) {
    name = n;
    discount = d;
    comboItems = list;
  }
  
  public String getName() {
    return name;
  }
}
