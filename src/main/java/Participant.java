/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "SnowShoeTourStateMachine.ump"
public class Participant
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Participant State Machines
  public enum Status { NotAssigned, Assigned, Paid, Started, Cancelled, Finished }
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Participant()
  {
    setStatus(Status.NotAssigned);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean assign(Tour tour)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotAssigned:
        // line 5 "SnowShoeTourStateMachine.ump"
        doAssign(tour);
        setStatus(Status.Assigned);
        wasEventProcessed = true;
        break;
      case Assigned:
        // line 18 "SnowShoeTourStateMachine.ump"
        rejectAssign(tour);
        setStatus(Status.Assigned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case NotAssigned:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Assigned:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        // line 27 "SnowShoeTourStateMachine.ump"
        doRefund(50);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Started:
        // line 31 "SnowShoeTourStateMachine.ump"
        doRefund(10);
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean pay(String authorizationCode)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        if (isValid(authorizationCode))
        {
        // line 12 "SnowShoeTourStateMachine.ump"
          doPay(authorizationCode);
          setStatus(Status.Paid);
          wasEventProcessed = true;
          break;
        }
        if (!(isValid(authorizationCode)))
        {
        // line 15 "SnowShoeTourStateMachine.ump"
          rejectPay(authorizationCode);
          setStatus(Status.Assigned);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startTrip(int week)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Assigned:
        setStatus(Status.Cancelled);
        wasEventProcessed = true;
        break;
      case Paid:
        if (hasMatchingStartWeek(week))
        {
          setStatus(Status.Started);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean finishTrip()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Started:
        setStatus(Status.Finished);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }

  public void delete()
  {}

  // line 38 "SnowShoeTourStateMachine.ump"
   private void doAssign(Tour tour){
    setTour(tour);
  }

  // line 42 "SnowShoeTourStateMachine.ump"
   private void rejectAssign(Tour tour){
    throw new RuntimeException("Assigning participant failed");
  }

  // line 46 "SnowShoeTourStateMachine.ump"
   private boolean isValid(String authorizationCode){
    if(authorizationCode.equals("") ||  authorizationCode == null) return false;
    return true;
  }

  // line 52 "SnowShoeTourStateMachine.ump"
   private void doPay(String authorizationCode){
    setStatus(Status.Paid);
      setAuthorizationCode(authorizationCode);
  }

  // line 57 "SnowShoeTourStateMachine.ump"
   private void rejectPay(String authorizationCode){
    throw new RuntimeException("Payement failed");
  }

  // line 61 "SnowShoeTourStateMachine.ump"
   private boolean hasMatchingStartWeek(int week){
    return (week >= getWeekAvailableFrom() && week <= getWeekAvailableUntil());
  }

  // line 65 "SnowShoeTourStateMachine.ump"
   private void doRefund(int refundedPercentageAmount){
    setRefundedPercentageAmount(refundedPercentageAmount);
  }

  // line 69 "SnowShoeTourStateMachine.ump"
   private void rejectRefund(int refundedPercentageAmount){
    throw new RuntimeException("Refund failed");
  }

}