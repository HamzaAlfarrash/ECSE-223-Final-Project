/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 66 "../../../../../SST.ump"
public class Combo extends RentItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Combo Attributes
  private int discount;
  private int totalPricePerWeek;

  //Combo Associations
  private SnowShoeTours snowShoeTours;
  private List<BookedGear> bookedGears;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Combo(String aName, int aDiscount, int aTotalPricePerWeek, SnowShoeTours aSnowShoeTours)
  {
    super(aName);
    discount = aDiscount;
    totalPricePerWeek = aTotalPricePerWeek;
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create combo due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    bookedGears = new ArrayList<BookedGear>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDiscount(int aDiscount)
  {
    boolean wasSet = false;
    discount = aDiscount;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalPricePerWeek(int aTotalPricePerWeek)
  {
    boolean wasSet = false;
    totalPricePerWeek = aTotalPricePerWeek;
    wasSet = true;
    return wasSet;
  }

  public int getDiscount()
  {
    return discount;
  }

  public int getTotalPricePerWeek()
  {
    return totalPricePerWeek;
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
      existingSnowShoeTours.removeCombo(this);
    }
    snowShoeTours.addCombo(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfBookedGearsValid()
  {
    boolean isValid = numberOfBookedGears() >= minimumNumberOfBookedGears();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedGears()
  {
    return 2;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public BookedGear addBookedGear(int aQuantity, SnowShoeTours aSnowShoeTours, Gear aGear)
  {
    BookedGear aNewBookedGear = new BookedGear(aQuantity, aSnowShoeTours, this, aGear);
    return aNewBookedGear;
  }

  public boolean addBookedGear(BookedGear aBookedGear)
  {
    boolean wasAdded = false;
    if (bookedGears.contains(aBookedGear)) { return false; }
    Combo existingCombo = aBookedGear.getCombo();
    boolean isNewCombo = existingCombo != null && !this.equals(existingCombo);

    if (isNewCombo && existingCombo.numberOfBookedGears() <= minimumNumberOfBookedGears())
    {
      return wasAdded;
    }
    if (isNewCombo)
    {
      aBookedGear.setCombo(this);
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
    //Unable to remove aBookedGear, as it must always have a combo
    if (this.equals(aBookedGear.getCombo()))
    {
      return wasRemoved;
    }

    //combo already at minimum (2)
    if (numberOfBookedGears() <= minimumNumberOfBookedGears())
    {
      return wasRemoved;
    }

    bookedGears.remove(aBookedGear);
    wasRemoved = true;
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
      placeholderSnowShoeTours.removeCombo(this);
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
            "discount" + ":" + getDiscount()+ "," +
            "totalPricePerWeek" + ":" + getTotalPricePerWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null");
  }
}