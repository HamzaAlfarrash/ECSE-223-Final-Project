/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 73 "../../../../../SST.ump"
public class Gear extends RentItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Gear Attributes
  private int pricePerWeek;

  //Gear Associations
  private SnowShoeTours snowShoeTours;
  private List<BookedGear> bookedGears;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Gear(String aName, int aPricePerWeek, SnowShoeTours aSnowShoeTours)
  {
    super(aName);
    pricePerWeek = aPricePerWeek;
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create gear due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bookedGears = new ArrayList<BookedGear>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPricePerWeek(int aPricePerWeek)
  {
    boolean wasSet = false;
    pricePerWeek = aPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public int getPricePerWeek()
  {
    return pricePerWeek;
  }
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_GetMany */
  public BookedGear getBookedGear(int index)
  {
    BookedGear aBookedGear = bookedGears.get(index);
    return aBookedGear;
  }

  public List<BookedGear> getBookedGears()
  {
    List<BookedGear> newBookedGears = Collections.unmodifiableList(bookedGears);
    return newBookedGears;
  }

  public int numberOfBookedGears()
  {
    int number = bookedGears.size();
    return number;
  }

  public boolean hasBookedGears()
  {
    boolean has = bookedGears.size() > 0;
    return has;
  }

  public int indexOfBookedGear(BookedGear aBookedGear)
  {
    int index = bookedGears.indexOf(aBookedGear);
    return index;
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
      existingSnowShoeTours.removeGear(this);
    }
    snowShoeTours.addGear(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedGears()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BookedGear addBookedGear(int aQuantity, SnowShoeTours aSnowShoeTours, Combo aCombo)
  {
    return new BookedGear(aQuantity, aSnowShoeTours, aCombo, this);
  }

  public boolean addBookedGear(BookedGear aBookedGear)
  {
    boolean wasAdded = false;
    if (bookedGears.contains(aBookedGear)) { return false; }
    Gear existingGear = aBookedGear.getGear();
    boolean isNewGear = existingGear != null && !this.equals(existingGear);
    if (isNewGear)
    {
      aBookedGear.setGear(this);
    }
    else
    {
      bookedGears.add(aBookedGear);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBookedGear(BookedGear aBookedGear)
  {
    boolean wasRemoved = false;
    //Unable to remove aBookedGear, as it must always have a gear
    if (!this.equals(aBookedGear.getGear()))
    {
      bookedGears.remove(aBookedGear);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBookedGearAt(BookedGear aBookedGear, int index)
  {  
    boolean wasAdded = false;
    if(addBookedGear(aBookedGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedGears()) { index = numberOfBookedGears() - 1; }
      bookedGears.remove(aBookedGear);
      bookedGears.add(index, aBookedGear);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookedGearAt(BookedGear aBookedGear, int index)
  {
    boolean wasAdded = false;
    if(bookedGears.contains(aBookedGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedGears()) { index = numberOfBookedGears() - 1; }
      bookedGears.remove(aBookedGear);
      bookedGears.add(index, aBookedGear);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookedGearAt(aBookedGear, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    SnowShoeTours placeholderSnowShoeTours = snowShoeTours;
    this.snowShoeTours = null;
    if(placeholderSnowShoeTours != null)
    {
      placeholderSnowShoeTours.removeGear(this);
    }
    for(int i=bookedGears.size(); i > 0; i--)
    {
      BookedGear aBookedGear = bookedGears.get(i - 1);
      aBookedGear.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "pricePerWeek" + ":" + getPricePerWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null");
  }
}