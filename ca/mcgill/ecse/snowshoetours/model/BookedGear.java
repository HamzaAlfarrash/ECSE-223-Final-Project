/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;

// line 94 "../../../../../SST.ump"
public class BookedGear
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BookedGear Attributes
  private int quantity;

  //BookedGear Associations
  private SnowShoeTours snowShoeTours;
  private Combo combo;
  private Gear gear;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetCombo;
  private boolean canSetGear;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BookedGear(int aQuantity, SnowShoeTours aSnowShoeTours, Combo aCombo, Gear aGear)
  {
    cachedHashCode = -1;
    canSetCombo = true;
    canSetGear = true;
    quantity = aQuantity;
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create bookedGear due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCombo = setCombo(aCombo);
    if (!didAddCombo)
    {
      throw new RuntimeException("Unable to create bookedGear due to combo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGear = setGear(aGear);
    if (!didAddGear)
    {
      throw new RuntimeException("Unable to create bookedGear due to gear. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public int getQuantity()
  {
    return quantity;
  }
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_GetOne */
  public Combo getCombo()
  {
    return combo;
  }
  /* Code from template association_GetOne */
  public Gear getGear()
  {
    return gear;
  }
  /* Code from template association_SetOneToManyAssociationClass */
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
      existingSnowShoeTours.removeBookedGear(this);
    }
    if (!snowShoeTours.addBookedGear(this))
    {
      snowShoeTours = existingSnowShoeTours;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setCombo(Combo aCombo)
  {
    boolean wasSet = false;
    if (!canSetCombo) { return false; }
    //Must provide combo to bookedGear
    if (aCombo == null)
    {
      return wasSet;
    }

    if (combo != null && combo.numberOfBookedGears() <= Combo.minimumNumberOfBookedGears())
    {
      return wasSet;
    }

    Combo existingCombo = combo;
    combo = aCombo;
    if (existingCombo != null && !existingCombo.equals(aCombo))
    {
      boolean didRemove = existingCombo.removeBookedGear(this);
      if (!didRemove)
      {
        combo = existingCombo;
        return wasSet;
      }
    }
    combo.addBookedGear(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setGear(Gear aGear)
  {
    boolean wasSet = false;
    if (!canSetGear) { return false; }
    if (aGear == null)
    {
      return wasSet;
    }

    Gear existingGear = gear;
    gear = aGear;
    if (existingGear != null && !existingGear.equals(aGear))
    {
      existingGear.removeBookedGear(this);
    }
    if (!gear.addBookedGear(this))
    {
      gear = existingGear;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    BookedGear compareTo = (BookedGear)obj;
  
    if (getCombo() == null && compareTo.getCombo() != null)
    {
      return false;
    }
    else if (getCombo() != null && !getCombo().equals(compareTo.getCombo()))
    {
      return false;
    }

    if (getGear() == null && compareTo.getGear() != null)
    {
      return false;
    }
    else if (getGear() != null && !getGear().equals(compareTo.getGear()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getCombo() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getCombo().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getGear() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getGear().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetCombo = false;
    canSetGear = false;
    return cachedHashCode;
  }

  public void delete()
  {
    SnowShoeTours placeholderSnowShoeTours = snowShoeTours;
    this.snowShoeTours = null;
    if(placeholderSnowShoeTours != null)
    {
      placeholderSnowShoeTours.removeBookedGear(this);
    }
    Combo placeholderCombo = combo;
    this.combo = null;
    if(placeholderCombo != null)
    {
      placeholderCombo.removeBookedGear(this);
    }
    Gear placeholderGear = gear;
    this.gear = null;
    if(placeholderGear != null)
    {
      placeholderGear.removeBookedGear(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "combo = "+(getCombo()!=null?Integer.toHexString(System.identityHashCode(getCombo())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gear = "+(getGear()!=null?Integer.toHexString(System.identityHashCode(getGear())):"null");
  }
}