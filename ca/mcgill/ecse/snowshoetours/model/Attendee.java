/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;

// line 29 "../../../../../SST.ump"
public class Attendee extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Attendee Attributes
  private String emergencyContact;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Attendee(SnowShoeTours aSnowShoeTours, User aUser, String aEmergencyContact)
  {
    super(aSnowShoeTours, aUser);
    emergencyContact = aEmergencyContact;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmergencyContact(String aEmergencyContact)
  {
    boolean wasSet = false;
    emergencyContact = aEmergencyContact;
    wasSet = true;
    return wasSet;
  }

  public String getEmergencyContact()
  {
    return emergencyContact;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "emergencyContact" + ":" + getEmergencyContact()+ "]";
  }
}