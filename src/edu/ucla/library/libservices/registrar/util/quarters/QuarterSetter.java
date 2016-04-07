package edu.ucla.library.libservices.registrar.util.quarters;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class QuarterSetter
{
  private GregorianCalendar today;
  private int month, year;

  public QuarterSetter()
  {
    today = new GregorianCalendar();
    month = today.get( Calendar.MONTH ) + 1;
    year = today.get( Calendar.YEAR ) - 2000;

    if ( month == 12 )
      year += 1;
  }

  public String getTerm1()
  {
    return getYearPart().concat(getQuarterPart(true));
  }

  public String getTerm2()
  {
    return getYearPart().concat(getQuarterPart(false));
  }

  private String getYearPart()
  {
    return ( year < 10? "0" : "" ).concat( String.valueOf( year ) );
  }
  
  private String getQuarterPart(boolean forFirstTerm)
  {
    String quarterPart;
    switch ( month )
    {
      case 1 :
      case 2 :
      case 12 : 
        quarterPart = ( forFirstTerm ? "W" : "S" );
        break;
      case 3 :
      case 4 :
        quarterPart = "S";
        break;
      case 5 :
      case 6 :
      case 7 :
      case 8 :
        quarterPart = ( forFirstTerm ? "S" : "1" );
        break;
      case 9 :
      case 10 :
      case 11 :
        quarterPart = "F";
        break;
      default : 
        quarterPart = "";
    }
    
    return quarterPart;
  }
}
