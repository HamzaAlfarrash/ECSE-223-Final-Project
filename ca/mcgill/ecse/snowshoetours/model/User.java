/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 101 "../../../../../SST.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private String email;
  private String password;

  //User Associations
  private UserRole userRole;
  private SnowShoeTours snowShoeTours;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, String aEmail, String aPassword, SnowShoeTours aSnowShoeTours)
  {
    name = aName;
    password = aPassword;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create user due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }
  /* Code from template association_GetOne */
  public UserRole getUserRole()
  {
    return userRole;
  }

  public boolean hasUserRole()
  {
    boolean has = userRole != null;
    return has;
  }
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setUserRole(UserRole aNewUserRole)
  {
    boolean wasSet = false;
    if (userRole != null && !userRole.equals(aNewUserRole) && equals(userRole.getUser()))
    {
      //Unable to setUserRole, as existing userRole would become an orphan
      return wasSet;
    }

    userRole = aNewUserRole;
    User anOldUser = aNewUserRole != null ? aNewUserRole.getUser() : null;

    if (!this.equals(anOldUser))
    {
      if (anOldUser != null)
      {
        anOldUser.userRole = null;
      }
      if (userRole != null)
      {
        userRole.setUser(this);
      }
    }
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
      existingSnowShoeTours.removeUser(this);
    }
    snowShoeTours.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    UserRole existingUserRole = userRole;
    userRole = null;
    if (existingUserRole != null)
    {
      existingUserRole.delete();
    }
    SnowShoeTours placeholderSnowShoeTours = snowShoeTours;
    this.snowShoeTours = null;
    if(placeholderSnowShoeTours != null)
    {
      placeholderSnowShoeTours.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "userRole = "+(getUserRole()!=null?Integer.toHexString(System.identityHashCode(getUserRole())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null");
  }
}