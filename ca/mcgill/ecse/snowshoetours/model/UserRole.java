/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;

// line 20 "../../../../../SST.ump"
public abstract class UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //UserRole Associations
  private SnowShoeTours snowShoeTours;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public UserRole(SnowShoeTours aSnowShoeTours, User aUser)
  {
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create userRole due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create userRole due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
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
      existingSnowShoeTours.removeUserRole(this);
    }
    snowShoeTours.addUserRole(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setUser(User aNewUser)
  {
    boolean wasSet = false;
    if (aNewUser == null)
    {
      //Unable to setUser to null, as userRole must always be associated to a user
      return wasSet;
    }
    
    UserRole existingUserRole = aNewUser.getUserRole();
    if (existingUserRole != null && !equals(existingUserRole))
    {
      //Unable to setUser, the current user already has a userRole, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    User anOldUser = user;
    user = aNewUser;
    user.setUserRole(this);

    if (anOldUser != null)
    {
      anOldUser.setUserRole(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    SnowShoeTours placeholderSnowShoeTours = snowShoeTours;
    this.snowShoeTours = null;
    if(placeholderSnowShoeTours != null)
    {
      placeholderSnowShoeTours.removeUserRole(this);
    }
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.setUserRole(null);
    }
  }

}