/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 60 "../../../../../SST.ump"
public class SpecificItem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, SpecificItem> specificitemsByBookingNum = new HashMap<String, SpecificItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SpecificItem Attributes
  private String bookingNum;

  //SpecificItem Associations
  private Participant participant;
  private SnowShoeTours snowShoeTours;
  private RentItem rentItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SpecificItem(String aBookingNum, Participant aParticipant, SnowShoeTours aSnowShoeTours, RentItem aRentItem)
  {
    if (!setBookingNum(aBookingNum))
    {
      throw new RuntimeException("Cannot create due to duplicate bookingNum. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddParticipant = setParticipant(aParticipant);
    if (!didAddParticipant)
    {
      throw new RuntimeException("Unable to create item due to participant. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create specificItem due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddRentItem = setRentItem(aRentItem);
    if (!didAddRentItem)
    {
      throw new RuntimeException("Unable to create item due to rentItem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBookingNum(String aBookingNum)
  {
    boolean wasSet = false;
    String anOldBookingNum = getBookingNum();
    if (anOldBookingNum != null && anOldBookingNum.equals(aBookingNum)) {
      return true;
    }
    if (hasWithBookingNum(aBookingNum)) {
      return wasSet;
    }
    bookingNum = aBookingNum;
    wasSet = true;
    if (anOldBookingNum != null) {
      specificitemsByBookingNum.remove(anOldBookingNum);
    }
    specificitemsByBookingNum.put(aBookingNum, this);
    return wasSet;
  }

  public String getBookingNum()
  {
    return bookingNum;
  }
  /* Code from template attribute_GetUnique */
  public static SpecificItem getWithBookingNum(String aBookingNum)
  {
    return specificitemsByBookingNum.get(aBookingNum);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithBookingNum(String aBookingNum)
  {
    return getWithBookingNum(aBookingNum) != null;
  }
  /* Code from template association_GetOne */
  public Participant getParticipant()
  {
    return participant;
  }
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_GetOne */
  public RentItem getRentItem()
  {
    return rentItem;
  }
  /* Code from template association_SetOneToMany */
  public boolean setParticipant(Participant aParticipant)
  {
    boolean wasSet = false;
    if (aParticipant == null)
    {
      return wasSet;
    }

    Participant existingParticipant = participant;
    participant = aParticipant;
    if (existingParticipant != null && !existingParticipant.equals(aParticipant))
    {
      existingParticipant.removeItem(this);
    }
    participant.addItem(this);
    wasSet = true;
    return wasSet;
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
      existingSnowShoeTours.removeSpecificItem(this);
    }
    snowShoeTours.addSpecificItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setRentItem(RentItem aRentItem)
  {
    boolean wasSet = false;
    if (aRentItem == null)
    {
      return wasSet;
    }

    RentItem existingRentItem = rentItem;
    rentItem = aRentItem;
    if (existingRentItem != null && !existingRentItem.equals(aRentItem))
    {
      existingRentItem.removeItem(this);
    }
    rentItem.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    specificitemsByBookingNum.remove(getBookingNum());
    Participant placeholderParticipant = participant;
    this.participant = null;
    if(placeholderParticipant != null)
    {
      placeholderParticipant.removeItem(this);
    }
    SnowShoeTours placeholderSnowShoeTours = snowShoeTours;
    this.snowShoeTours = null;
    if(placeholderSnowShoeTours != null)
    {
      placeholderSnowShoeTours.removeSpecificItem(this);
    }
    RentItem placeholderRentItem = rentItem;
    this.rentItem = null;
    if(placeholderRentItem != null)
    {
      placeholderRentItem.removeItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "bookingNum" + ":" + getBookingNum()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "participant = "+(getParticipant()!=null?Integer.toHexString(System.identityHashCode(getParticipant())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "rentItem = "+(getRentItem()!=null?Integer.toHexString(System.identityHashCode(getRentItem())):"null");
  }
}