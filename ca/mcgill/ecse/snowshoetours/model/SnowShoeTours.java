/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.sql.Date;
import java.util.*;

// line 3 "../../../../../SST.ump"
public class SnowShoeTours
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SnowShoeTours Attributes
  private Date startDate;
  private Date endDate;
  private int nbOfWeeks;
  private int guidePrice;

  //SnowShoeTours Associations
  private List<User> user;
  private List<Lodge> lodge;
  private List<Gear> gear;
  private List<Combo> combo;
  private List<SpecificItem> specificItem;
  private List<UserRole> userRole;
  private List<BookedGear> bookedGear;
  private List<Tour> tour;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SnowShoeTours(Date aStartDate, Date aEndDate, int aNbOfWeeks, int aGuidePrice)
  {
    startDate = aStartDate;
    endDate = aEndDate;
    nbOfWeeks = aNbOfWeeks;
    guidePrice = aGuidePrice;
    user = new ArrayList<User>();
    lodge = new ArrayList<Lodge>();
    gear = new ArrayList<Gear>();
    combo = new ArrayList<Combo>();
    specificItem = new ArrayList<SpecificItem>();
    userRole = new ArrayList<UserRole>();
    bookedGear = new ArrayList<BookedGear>();
    tour = new ArrayList<Tour>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartDate(Date aStartDate)
  {
    boolean wasSet = false;
    startDate = aStartDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndDate(Date aEndDate)
  {
    boolean wasSet = false;
    endDate = aEndDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNbOfWeeks(int aNbOfWeeks)
  {
    boolean wasSet = false;
    nbOfWeeks = aNbOfWeeks;
    wasSet = true;
    return wasSet;
  }

  public boolean setGuidePrice(int aGuidePrice)
  {
    boolean wasSet = false;
    guidePrice = aGuidePrice;
    wasSet = true;
    return wasSet;
  }

  public Date getStartDate()
  {
    return startDate;
  }

  public Date getEndDate()
  {
    return endDate;
  }

  public int getNbOfWeeks()
  {
    return nbOfWeeks;
  }

  public int getGuidePrice()
  {
    return guidePrice;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = user.get(index);
    return aUser;
  }

  public List<User> getUser()
  {
    List<User> newUser = Collections.unmodifiableList(user);
    return newUser;
  }

  public int numberOfUser()
  {
    int number = user.size();
    return number;
  }

  public boolean hasUser()
  {
    boolean has = user.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = user.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Lodge getLodge(int index)
  {
    Lodge aLodge = lodge.get(index);
    return aLodge;
  }

  public List<Lodge> getLodge()
  {
    List<Lodge> newLodge = Collections.unmodifiableList(lodge);
    return newLodge;
  }

  public int numberOfLodge()
  {
    int number = lodge.size();
    return number;
  }

  public boolean hasLodge()
  {
    boolean has = lodge.size() > 0;
    return has;
  }

  public int indexOfLodge(Lodge aLodge)
  {
    int index = lodge.indexOf(aLodge);
    return index;
  }
  /* Code from template association_GetMany */
  public Gear getGear(int index)
  {
    Gear aGear = gear.get(index);
    return aGear;
  }

  public List<Gear> getGear()
  {
    List<Gear> newGear = Collections.unmodifiableList(gear);
    return newGear;
  }

  public int numberOfGear()
  {
    int number = gear.size();
    return number;
  }

  public boolean hasGear()
  {
    boolean has = gear.size() > 0;
    return has;
  }

  public int indexOfGear(Gear aGear)
  {
    int index = gear.indexOf(aGear);
    return index;
  }
  /* Code from template association_GetMany */
  public Combo getCombo(int index)
  {
    Combo aCombo = combo.get(index);
    return aCombo;
  }

  public List<Combo> getCombo()
  {
    List<Combo> newCombo = Collections.unmodifiableList(combo);
    return newCombo;
  }

  public int numberOfCombo()
  {
    int number = combo.size();
    return number;
  }

  public boolean hasCombo()
  {
    boolean has = combo.size() > 0;
    return has;
  }

  public int indexOfCombo(Combo aCombo)
  {
    int index = combo.indexOf(aCombo);
    return index;
  }
  /* Code from template association_GetMany */
  public SpecificItem getSpecificItem(int index)
  {
    SpecificItem aSpecificItem = specificItem.get(index);
    return aSpecificItem;
  }

  public List<SpecificItem> getSpecificItem()
  {
    List<SpecificItem> newSpecificItem = Collections.unmodifiableList(specificItem);
    return newSpecificItem;
  }

  public int numberOfSpecificItem()
  {
    int number = specificItem.size();
    return number;
  }

  public boolean hasSpecificItem()
  {
    boolean has = specificItem.size() > 0;
    return has;
  }

  public int indexOfSpecificItem(SpecificItem aSpecificItem)
  {
    int index = specificItem.indexOf(aSpecificItem);
    return index;
  }
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRole.get(index);
    return aUserRole;
  }

  public List<UserRole> getUserRole()
  {
    List<UserRole> newUserRole = Collections.unmodifiableList(userRole);
    return newUserRole;
  }

  public int numberOfUserRole()
  {
    int number = userRole.size();
    return number;
  }

  public boolean hasUserRole()
  {
    boolean has = userRole.size() > 0;
    return has;
  }

  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRole.indexOf(aUserRole);
    return index;
  }
  /* Code from template association_GetMany */
  public BookedGear getBookedGear(int index)
  {
    BookedGear aBookedGear = bookedGear.get(index);
    return aBookedGear;
  }

  public List<BookedGear> getBookedGear()
  {
    List<BookedGear> newBookedGear = Collections.unmodifiableList(bookedGear);
    return newBookedGear;
  }

  public int numberOfBookedGear()
  {
    int number = bookedGear.size();
    return number;
  }

  public boolean hasBookedGear()
  {
    boolean has = bookedGear.size() > 0;
    return has;
  }

  public int indexOfBookedGear(BookedGear aBookedGear)
  {
    int index = bookedGear.indexOf(aBookedGear);
    return index;
  }
  /* Code from template association_GetMany */
  public Tour getTour(int index)
  {
    Tour aTour = tour.get(index);
    return aTour;
  }

  public List<Tour> getTour()
  {
    List<Tour> newTour = Collections.unmodifiableList(tour);
    return newTour;
  }

  public int numberOfTour()
  {
    int number = tour.size();
    return number;
  }

  public boolean hasTour()
  {
    boolean has = tour.size() > 0;
    return has;
  }

  public int indexOfTour(Tour aTour)
  {
    int index = tour.indexOf(aTour);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUser()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aName, String aEmail, String aPassword)
  {
    return new User(aName, aEmail, aPassword, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (user.contains(aUser)) { return false; }
    SnowShoeTours existingSnowShoeTours = aUser.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aUser.setSnowShoeTours(this);
    }
    else
    {
      user.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a snowShoeTours
    if (!this.equals(aUser.getSnowShoeTours()))
    {
      user.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(user.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfLodge()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Lodge addLodge(Lodge.Rating aRating, String aName, String aAdress)
  {
    return new Lodge(aRating, aName, aAdress, this);
  }

  public boolean addLodge(Lodge aLodge)
  {
    boolean wasAdded = false;
    if (lodge.contains(aLodge)) { return false; }
    SnowShoeTours existingSnowShoeTours = aLodge.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aLodge.setSnowShoeTours(this);
    }
    else
    {
      lodge.add(aLodge);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeLodge(Lodge aLodge)
  {
    boolean wasRemoved = false;
    //Unable to remove aLodge, as it must always have a snowShoeTours
    if (!this.equals(aLodge.getSnowShoeTours()))
    {
      lodge.remove(aLodge);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addLodgeAt(Lodge aLodge, int index)
  {  
    boolean wasAdded = false;
    if(addLodge(aLodge))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodge()) { index = numberOfLodge() - 1; }
      lodge.remove(aLodge);
      lodge.add(index, aLodge);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveLodgeAt(Lodge aLodge, int index)
  {
    boolean wasAdded = false;
    if(lodge.contains(aLodge))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfLodge()) { index = numberOfLodge() - 1; }
      lodge.remove(aLodge);
      lodge.add(index, aLodge);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addLodgeAt(aLodge, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGear()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Gear addGear(String aName, int aPricePerWeek)
  {
    return new Gear(aName, aPricePerWeek, this);
  }

  public boolean addGear(Gear aGear)
  {
    boolean wasAdded = false;
    if (gear.contains(aGear)) { return false; }
    SnowShoeTours existingSnowShoeTours = aGear.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aGear.setSnowShoeTours(this);
    }
    else
    {
      gear.add(aGear);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGear(Gear aGear)
  {
    boolean wasRemoved = false;
    //Unable to remove aGear, as it must always have a snowShoeTours
    if (!this.equals(aGear.getSnowShoeTours()))
    {
      gear.remove(aGear);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGearAt(Gear aGear, int index)
  {  
    boolean wasAdded = false;
    if(addGear(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGear()) { index = numberOfGear() - 1; }
      gear.remove(aGear);
      gear.add(index, aGear);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGearAt(Gear aGear, int index)
  {
    boolean wasAdded = false;
    if(gear.contains(aGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGear()) { index = numberOfGear() - 1; }
      gear.remove(aGear);
      gear.add(index, aGear);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGearAt(aGear, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombo()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Combo addCombo(String aName, int aDiscount, int aTotalPricePerWeek)
  {
    return new Combo(aName, aDiscount, aTotalPricePerWeek, this);
  }

  public boolean addCombo(Combo aCombo)
  {
    boolean wasAdded = false;
    if (combo.contains(aCombo)) { return false; }
    SnowShoeTours existingSnowShoeTours = aCombo.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aCombo.setSnowShoeTours(this);
    }
    else
    {
      combo.add(aCombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCombo(Combo aCombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aCombo, as it must always have a snowShoeTours
    if (!this.equals(aCombo.getSnowShoeTours()))
    {
      combo.remove(aCombo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addComboAt(Combo aCombo, int index)
  {  
    boolean wasAdded = false;
    if(addCombo(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo()) { index = numberOfCombo() - 1; }
      combo.remove(aCombo);
      combo.add(index, aCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveComboAt(Combo aCombo, int index)
  {
    boolean wasAdded = false;
    if(combo.contains(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo()) { index = numberOfCombo() - 1; }
      combo.remove(aCombo);
      combo.add(index, aCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addComboAt(aCombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSpecificItem()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public SpecificItem addSpecificItem(String aBookingNum, Participant aParticipant, RentItem aRentItem)
  {
    return new SpecificItem(aBookingNum, aParticipant, this, aRentItem);
  }

  public boolean addSpecificItem(SpecificItem aSpecificItem)
  {
    boolean wasAdded = false;
    if (specificItem.contains(aSpecificItem)) { return false; }
    SnowShoeTours existingSnowShoeTours = aSpecificItem.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aSpecificItem.setSnowShoeTours(this);
    }
    else
    {
      specificItem.add(aSpecificItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSpecificItem(SpecificItem aSpecificItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aSpecificItem, as it must always have a snowShoeTours
    if (!this.equals(aSpecificItem.getSnowShoeTours()))
    {
      specificItem.remove(aSpecificItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSpecificItemAt(SpecificItem aSpecificItem, int index)
  {  
    boolean wasAdded = false;
    if(addSpecificItem(aSpecificItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificItem()) { index = numberOfSpecificItem() - 1; }
      specificItem.remove(aSpecificItem);
      specificItem.add(index, aSpecificItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSpecificItemAt(SpecificItem aSpecificItem, int index)
  {
    boolean wasAdded = false;
    if(specificItem.contains(aSpecificItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSpecificItem()) { index = numberOfSpecificItem() - 1; }
      specificItem.remove(aSpecificItem);
      specificItem.add(index, aSpecificItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSpecificItemAt(aSpecificItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRole()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRole.contains(aUserRole)) { return false; }
    SnowShoeTours existingSnowShoeTours = aUserRole.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aUserRole.setSnowShoeTours(this);
    }
    else
    {
      userRole.add(aUserRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserRole, as it must always have a snowShoeTours
    if (!this.equals(aUserRole.getSnowShoeTours()))
    {
      userRole.remove(aUserRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRole()) { index = numberOfUserRole() - 1; }
      userRole.remove(aUserRole);
      userRole.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRole.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRole()) { index = numberOfUserRole() - 1; }
      userRole.remove(aUserRole);
      userRole.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBookedGear()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BookedGear addBookedGear(int aQuantity, Combo aCombo, Gear aGear)
  {
    return new BookedGear(aQuantity, this, aCombo, aGear);
  }

  public boolean addBookedGear(BookedGear aBookedGear)
  {
    boolean wasAdded = false;
    if (bookedGear.contains(aBookedGear)) { return false; }
    SnowShoeTours existingSnowShoeTours = aBookedGear.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aBookedGear.setSnowShoeTours(this);
    }
    else
    {
      bookedGear.add(aBookedGear);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBookedGear(BookedGear aBookedGear)
  {
    boolean wasRemoved = false;
    //Unable to remove aBookedGear, as it must always have a snowShoeTours
    if (!this.equals(aBookedGear.getSnowShoeTours()))
    {
      bookedGear.remove(aBookedGear);
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
      if(index > numberOfBookedGear()) { index = numberOfBookedGear() - 1; }
      bookedGear.remove(aBookedGear);
      bookedGear.add(index, aBookedGear);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBookedGearAt(BookedGear aBookedGear, int index)
  {
    boolean wasAdded = false;
    if(bookedGear.contains(aBookedGear))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBookedGear()) { index = numberOfBookedGear() - 1; }
      bookedGear.remove(aBookedGear);
      bookedGear.add(index, aBookedGear);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBookedGearAt(aBookedGear, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTour()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Tour addTour(int aStartWeek, int aEndWeek, Guide aGuide)
  {
    return new Tour(aStartWeek, aEndWeek, this, aGuide);
  }

  public boolean addTour(Tour aTour)
  {
    boolean wasAdded = false;
    if (tour.contains(aTour)) { return false; }
    SnowShoeTours existingSnowShoeTours = aTour.getSnowShoeTours();
    boolean isNewSnowShoeTours = existingSnowShoeTours != null && !this.equals(existingSnowShoeTours);
    if (isNewSnowShoeTours)
    {
      aTour.setSnowShoeTours(this);
    }
    else
    {
      tour.add(aTour);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTour(Tour aTour)
  {
    boolean wasRemoved = false;
    //Unable to remove aTour, as it must always have a snowShoeTours
    if (!this.equals(aTour.getSnowShoeTours()))
    {
      tour.remove(aTour);
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
      if(index > numberOfTour()) { index = numberOfTour() - 1; }
      tour.remove(aTour);
      tour.add(index, aTour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTourAt(Tour aTour, int index)
  {
    boolean wasAdded = false;
    if(tour.contains(aTour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTour()) { index = numberOfTour() - 1; }
      tour.remove(aTour);
      tour.add(index, aTour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTourAt(aTour, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (user.size() > 0)
    {
      User aUser = user.get(user.size() - 1);
      aUser.delete();
      user.remove(aUser);
    }
    
    while (lodge.size() > 0)
    {
      Lodge aLodge = lodge.get(lodge.size() - 1);
      aLodge.delete();
      lodge.remove(aLodge);
    }
    
    while (gear.size() > 0)
    {
      Gear aGear = gear.get(gear.size() - 1);
      aGear.delete();
      gear.remove(aGear);
    }
    
    while (combo.size() > 0)
    {
      Combo aCombo = combo.get(combo.size() - 1);
      aCombo.delete();
      combo.remove(aCombo);
    }
    
    while (specificItem.size() > 0)
    {
      SpecificItem aSpecificItem = specificItem.get(specificItem.size() - 1);
      aSpecificItem.delete();
      specificItem.remove(aSpecificItem);
    }
    
    while (userRole.size() > 0)
    {
      UserRole aUserRole = userRole.get(userRole.size() - 1);
      aUserRole.delete();
      userRole.remove(aUserRole);
    }
    
    while (bookedGear.size() > 0)
    {
      BookedGear aBookedGear = bookedGear.get(bookedGear.size() - 1);
      aBookedGear.delete();
      bookedGear.remove(aBookedGear);
    }
    
    while (tour.size() > 0)
    {
      Tour aTour = tour.get(tour.size() - 1);
      aTour.delete();
      tour.remove(aTour);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "nbOfWeeks" + ":" + getNbOfWeeks()+ "," +
            "guidePrice" + ":" + getGuidePrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startDate" + "=" + (getStartDate() != null ? !getStartDate().equals(this)  ? getStartDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endDate" + "=" + (getEndDate() != null ? !getEndDate().equals(this)  ? getEndDate().toString().replaceAll("  ","    ") : "this" : "null");
  }
}