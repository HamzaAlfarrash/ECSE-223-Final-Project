/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 53 "../../../../../SST.ump"
public abstract class RentItem
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, RentItem> rentitemsByName = new HashMap<String, RentItem>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //RentItem Attributes
  private String name;

  //RentItem Associations
  private List<SpecificItem> items;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public RentItem(String aName)
  {
    if (!setName(aName))
    {
      throw new RuntimeException("Cannot create due to duplicate name. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    items = new ArrayList<SpecificItem>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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
      rentitemsByName.remove(anOldName);
    }
    rentitemsByName.put(aName, this);
    return wasSet;
  }

  public String getName()
  {
    return name;
  }
  /* Code from template attribute_GetUnique */
  public static RentItem getWithName(String aName)
  {
    return rentitemsByName.get(aName);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithName(String aName)
  {
    return getWithName(aName) != null;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificItem addItem(String aBookingNum, Participant aParticipant, SnowShoeTours aSnowShoeTours)
  {
    return new SpecificItem(aBookingNum, aParticipant, aSnowShoeTours, this);
  }

  public boolean addItem(SpecificItem aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    RentItem existingRentItem = aItem.getRentItem();
    boolean isNewRentItem = existingRentItem != null && !this.equals(existingRentItem);
    if (isNewRentItem)
    {
      aItem.setRentItem(this);
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
    //Unable to remove aItem, as it must always have a rentItem
    if (!this.equals(aItem.getRentItem()))
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
    rentitemsByName.remove(getName());
    for(int i=items.size(); i > 0; i--)
    {
      SpecificItem aItem = items.get(i - 1);
      aItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "]";
  }
}