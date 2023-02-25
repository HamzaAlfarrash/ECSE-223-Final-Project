/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 41 "../../../../../SST.ump"
public class Participant extends Attendee
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Participant> participantsByAuthorizationCode = new HashMap<Integer, Participant>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant Attributes
  private int nbWeeks;
  private int startWeekAvailable;
  private int endWeekAvailable;
  private int authorizationCode;
  private boolean lodge;
  private int refundPercentage;

  //Participant Associations
  private Tour tour;
  private List<SpecificItem> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant(SnowShoeTours aSnowShoeTours, User aUser, String aEmergencyContact, int aNbWeeks, int aStartWeekAvailable, int aEndWeekAvailable, int aAuthorizationCode, boolean aLodge, int aRefundPercentage)
  {
    super(aSnowShoeTours, aUser, aEmergencyContact);
    nbWeeks = aNbWeeks;
    startWeekAvailable = aStartWeekAvailable;
    endWeekAvailable = aEndWeekAvailable;
    lodge = aLodge;
    refundPercentage = aRefundPercentage;
    if (!setAuthorizationCode(aAuthorizationCode))
    {
      throw new RuntimeException("Cannot create due to duplicate authorizationCode. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    items = new ArrayList<SpecificItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNbWeeks(int aNbWeeks)
  {
    boolean wasSet = false;
    nbWeeks = aNbWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartWeekAvailable(int aStartWeekAvailable)
  {
    boolean wasSet = false;
    startWeekAvailable = aStartWeekAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeekAvailable(int aEndWeekAvailable)
  {
    boolean wasSet = false;
    endWeekAvailable = aEndWeekAvailable;
    wasSet = true;
    return wasSet;
  }

  public boolean setAuthorizationCode(int aAuthorizationCode)
  {
    boolean wasSet = false;
    Integer anOldAuthorizationCode = getAuthorizationCode();
    if (anOldAuthorizationCode != null && anOldAuthorizationCode.equals(aAuthorizationCode)) {
      return true;
    }
    if (hasWithAuthorizationCode(aAuthorizationCode)) {
      return wasSet;
    }
    authorizationCode = aAuthorizationCode;
    wasSet = true;
    if (anOldAuthorizationCode != null) {
      participantsByAuthorizationCode.remove(anOldAuthorizationCode);
    }
    participantsByAuthorizationCode.put(aAuthorizationCode, this);
    return wasSet;
  }

  public boolean setLodge(boolean aLodge)
  {
    boolean wasSet = false;
    lodge = aLodge;
    wasSet = true;
    return wasSet;
  }

  public boolean setRefundPercentage(int aRefundPercentage)
  {
    boolean wasSet = false;
    refundPercentage = aRefundPercentage;
    wasSet = true;
    return wasSet;
  }

  public int getNbWeeks()
  {
    return nbWeeks;
  }

  public int getStartWeekAvailable()
  {
    return startWeekAvailable;
  }

  public int getEndWeekAvailable()
  {
    return endWeekAvailable;
  }

  public int getAuthorizationCode()
  {
    return authorizationCode;
  }
  /* Code from template attribute_GetUnique */
  public static Participant getWithAuthorizationCode(int aAuthorizationCode)
  {
    return participantsByAuthorizationCode.get(aAuthorizationCode);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithAuthorizationCode(int aAuthorizationCode)
  {
    return getWithAuthorizationCode(aAuthorizationCode) != null;
  }

  public boolean getLodge()
  {
    return lodge;
  }

  public int getRefundPercentage()
  {
    return refundPercentage;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isLodge()
  {
    return lodge;
  }
  /* Code from template association_GetOne */
  public Tour getTour()
  {
    return tour;
  }

  public boolean hasTour()
  {
    boolean has = tour != null;
    return has;
  }
  /* Code from template association_GetMany */
  public SpecificItem getItem(int index)
  {
    SpecificItem aItem = items.get(index);
    return aItem;
  }

  public List<SpecificItem> getItems()
  {
    List<SpecificItem> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(SpecificItem aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setTour(Tour aTour)
  {
    boolean wasSet = false;
    Tour existingTour = tour;
    tour = aTour;
    if (existingTour != null && !existingTour.equals(aTour))
    {
      existingTour.removeParticipant(this);
    }
    if (aTour != null)
    {
      aTour.addParticipant(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificItem addItem(String aBookingNum, SnowShoeTours aSnowShoeTours, RentItem aRentItem)
  {
    return new SpecificItem(aBookingNum, this, aSnowShoeTours, aRentItem);
  }

  public boolean addItem(SpecificItem aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    Participant existingParticipant = aItem.getParticipant();
    boolean isNewParticipant = existingParticipant != null && !this.equals(existingParticipant);
    if (isNewParticipant)
    {
      aItem.setParticipant(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(SpecificItem aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a participant
    if (!this.equals(aItem.getParticipant()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(SpecificItem aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(SpecificItem aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    participantsByAuthorizationCode.remove(getAuthorizationCode());
    if (tour != null)
    {
      Tour placeholderTour = tour;
      this.tour = null;
      placeholderTour.removeParticipant(this);
    }
    for(int i=items.size(); i > 0; i--)
    {
      SpecificItem aItem = items.get(i - 1);
      aItem.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "nbWeeks" + ":" + getNbWeeks()+ "," +
            "startWeekAvailable" + ":" + getStartWeekAvailable()+ "," +
            "endWeekAvailable" + ":" + getEndWeekAvailable()+ "," +
            "authorizationCode" + ":" + getAuthorizationCode()+ "," +
            "lodge" + ":" + getLodge()+ "," +
            "refundPercentage" + ":" + getRefundPercentage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tour = "+(getTour()!=null?Integer.toHexString(System.identityHashCode(getTour())):"null");
  }
}