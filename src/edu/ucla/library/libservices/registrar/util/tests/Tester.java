package edu.ucla.library.libservices.registrar.util.tests;

import edu.ucla.library.libservices.registrar.util.quarters.QuarterSetter;

public class Tester
{
  private static final String SRDB_SELECT = 
    "exec Rs_V022 @TERM_PARA1=:p1, @TERM_PARA2=:p2";

  public Tester()
  {
  }

  public static void main( String[] args )
  {
    QuarterSetter quarters;
    quarters = new QuarterSetter();
    
    System.out.println( "term1 = " + quarters.getTerm1() );
    System.out.println( "term2 = " + quarters.getTerm2() );
    
    System.out.println( SRDB_SELECT.replaceAll(":p1", quarters.getTerm1()).replaceAll(":p2", quarters.getTerm2()) );
  }
}
