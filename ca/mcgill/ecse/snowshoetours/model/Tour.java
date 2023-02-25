/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.snowshoetours.model;
import java.util.*;

// line 79 "../../../../../SST.ump"
public class Tour
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Tour Attributes
  private int startWeek;
  private int endWeek;

  //Tour Associations
  private SnowShoeTours snowShoeTours;
  private Guide guide;
  private List<Participant> participants;
  private Lodge lodge;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Tour(int aStartWeek, int aEndWeek, SnowShoeTours aSnowShoeTours, Guide aGuide)
  {
    startWeek = aStartWeek;
    endWeek = aEndWeek;
    boolean didAddSnowShoeTours = setSnowShoeTours(aSnowShoeTours);
    if (!didAddSnowShoeTours)
    {
      throw new RuntimeException("Unable to create tour due to snowShoeTours. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGuide = setGuide(aGuide);
    if (!didAddGuide)
    {
      throw new RuntimeException("Unable to create tour due to guide. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    participants = new ArrayList<Participant>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStartWeek(int aStartWeek)
  {
    boolean wasSet = false;
    startWeek = aStartWeek;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndWeek(int aEndWeek)
  {
    boolean wasSet = false;
    endWeek = aEndWeek;
    wasSet = true;
    return wasSet;
  }

  public int getStartWeek()
  {
    return startWeek;
  }

  public int getEndWeek()
  {
    return endWeek;
  }
  /* Code from template association_GetOne */
  public SnowShoeTours getSnowShoeTours()
  {
    return snowShoeTours;
  }
  /* Code from template association_GetOne */
  public Guide getGuide()
  {
    return guide;
  }
  /* Code from template association_GetMany */
  public Participant getParticipant(int index)
  {
    Participant aParticipant = participants.get(index);
    return aParticipant;
  }

  public List<Participant> getParticipants()
  {
    List<Participant> newParticipants = Collections.unmodifiableList(participants);
    return newParticipants;
  }

  public int numberOfParticipants()
  {
    int number = participants.size();
    return number;
  }

  public boolean hasParticipants()
  {
    boolean has = participants.size() > 0;
    return has;
  }

  public int indexOfParticipant(Participant aParticipant)
  {
    int index = participants.indexOf(aParticipant);
    return index;
  }
  /* Code from template association_GetOne */
  public Lodge getLodge()
  {
    return lodge;
  }

  public boolean hasLodge()
  {
    boolean has = lodge != null;
    return has;
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
      existingSnowShoeTours.removeTour(this);
    }
    snowShoeTours.addTour(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGuide(Guide aGuide)
  {
    boolean wasSet = false;
    if (aGuide == null)
    {
      return wasSet;
    }

    Guide existingGuide = guide;
    guide = aGuide;
    if (existingGuide != null && !existingGuide.equals(aGuide))
    {
      existingGuide.removeTour(this);
    }
    guide.addTour(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfParticipants()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addParticipant(Participant aParticipant)
  {
    boolean wasAdded = false;
    if (participants.contains(aParticipant)) { return false; }
    Tour existingTour = aParticipant.getTour();
    if (existingTour == null)
    {
      aParticipant.setTour(this);
    }
    else if (!this.equals(existingTour))
    {
      existingTour.removeParticipant(aParticipant);
      addParticipant(aParticipant);
    }
    else
    {
      participants.add(aParticipant);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeParticipant(Participant aParticipant)
  {
    boolean wasRemoved = false;
    if (participants.contains(aParticipant))
    {
      participants.remove(aParticipant);
      aParticipant.setTour(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addParticipantAt(Participant aParticipant, int index)
  {  
    boolean wasAdded = false;
    if(addParticipant(aParticipant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParticipants()) { index = numberOfParticipants() - 1; }
      participants.remove(aParticipant);
      participants.add(index, aParticipant);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveParticipantAt(Participant aParticipant, int index)
  {
    boolean wasAdded = false;
    if(participants.contains(aParticipant))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfParticipants()) { index = numberOfParticipants() - 1; }
      participants.remove(aParticipant);
      participants.add(index, aParticipant);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addParticipantAt(aParticipant, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setLodge(Lodge aLodge)
  {
    boolean wasSet = false;
    Lodge existingLodge = lodge;
    lodge = aLodge;
    if (existingLodge != null && !existingLodge.equals(aLodge))
    {
      existingLodge.removeTour(this);
    }
    if (aLodge != null)
    {
      aLodge.addTour(this);
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
      placeholderSnowShoeTours.removeTour(this);
    }
    Guide placeholderGuide = guide;
    this.guide = null;
    if(placeholderGuide != null)
    {
      placeholderGuide.removeTour(this);
    }
    while( !participants.isEmpty() )
    {
      participants.get(0).setTour(null);
    }
    if (lodge != null)
    {
      Lodge placeholderLodge = lodge;
      this.lodge = null;
      placeholderLodge.removeTour(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "startWeek" + ":" + getStartWeek()+ "," +
            "endWeek" + ":" + getEndWeek()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "snowShoeTours = "+(getSnowShoeTours()!=null?Integer.toHexString(System.identityHashCode(getSnowShoeTours())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "guide = "+(getGuide()!=null?Integer.toHexString(System.identityHashCode(getGuide())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "lodge = "+(getLodge()!=null?Integer.toHexString(System.identityHashCode(getLodge())):"null");
  }
}