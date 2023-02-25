/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 85 "../../../../../SST.ump"
public class Lodge
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Rating { 1, 2, 3, 4, 5 }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Lodge> lodgesByName = new HashMap<String, Lodge>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Lodge Attributes
  private Rating rating;
  private String name;
  private String adress;

  //Lodge Associations
  private List<Tour> tours;
  private SnowShoeTours snowShoeTours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Lodge(Rating aRating, String aName, String aAdress, SnowShoeTours aSnowShoeTours)
  {
    rating = aRating;
    adress = aAdress;
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    tours = new ArrayList<Tour>();
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create lodge due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRating(Rating aRating)
  {
    boolean wasSet = false;
    rating = aRating;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    String anOldName = getName();
    if (anOldName != null && anOldName.equals(aName)) {
      return true;
    }
    if (hasWithName(aName)) {
      return wasSet;
    }
    name = aName;
    wasSet = true;
    if (anOldName != null) {
      lodgesByName.remove(anOldName);
    }
    lodgesByName.put(aName, this);
    return wasSet;
  }

  public boolean setAdress(String aAdress)
  {
    boolean wasSet = false;
    adress = aAdress;
    wasSet = true;
    return wasSet;
  }

  public Rating getRating()
  {
    return rating;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static Lodge getWithName(String aName)
  {
    return lodgesByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
  }

  public String getAdress()
  {
    return adress;
  }
  /* Code from template association_GetMany */
  public Tour getTour(int index)
  {
    Tour aTour = tours.get(index);
    return aTour;
  }

  public List<Tour> getTours()
  {
    List<Tour> newTours = Collections.unmodifiableList(tours);
    return newTours;
  }

  public int numberOfTours()
  {
    int number = tours.size();
    return number;
  }

  public boolean hasTours()
  {
    boolean has = tours.size() > 0;
    return has;
  }

  public int indexOfTour(Tour aTour)
  {
    int index = tours.indexOf(aTour);
    return index;
  }
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tours.contains(aTour)) { return false; }
    Lodge existingLodge = aTour.getLodge();
    if (existingLodge == null)
    {
      aTour.setLodge(this);
    }
    else if (!this.equals(existingLodge))
    {
      existingLodge.removeTour(aTour);
      addTour(aTour);
    }
    else
    {
      tours.add(aTour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTour(Tour aTour)
  {
    boolean wasRemoved = false;
    if (tours.contains(aTour))
    {
      tours.remove(aTour);
      aTour.setLodge(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTourAt(Tour aTour, int index)
  {  
    boolean wasAdded = false;
    if(addTour(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTours()) { index = numberOfTours() - 1; }
      tours.remove(aTour);
      tours.add(index, aTour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTourAt(Tour aTour, int index)
  {
    boolean wasAdded = false;
    if(tours.contains(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTours()) { index = numberOfTours() - 1; }
      tours.remove(aTour);
      tours.add(index, aTour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTourAt(aTour, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSnowShoeTours(SnowShoeTours aSnowShoeTours)
  {
    boolean wasSet = false;
    if (aSnowShoeTours == null)
    {
      return wasSet;
    }

    SnowShoeTours existingSnowShoeTours = snowShoeTours;
    snowShoeTours = aSnowShoeTours;
    if (existingSnowShoeTours != null && !existingSnowShoeTours.equals(aSnowShoeTours))
    {
      existingSnowShoeTours.removeLodge(this);
    }
    snowShoeTours.addLodge(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    lodgesByName.remove(getName());
    while( !tours.isEmpty() )
    {
      tours.get(0).setLodge(null);
    }
    SnowShoeTours placeholderSnowShoeTours = snowShoeTours;
    this.snowShoeTours = null;
    if(placeholderSnowShoeTours != null)
    {
      placeholderSnowShoeTours.removeLodge(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "adress" + ":" + getAdress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "rating" + "=" + (getRating() != null ? !getRating().equals(this)  ? getRating().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null");
  }
}