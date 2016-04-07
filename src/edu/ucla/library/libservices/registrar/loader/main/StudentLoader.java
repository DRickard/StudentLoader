package edu.ucla.library.libservices.registrar.loader.main;

import edu.ucla.library.libservices.registrar.loader.beans.StudentBean;
import edu.ucla.library.libservices.registrar.loader.db.mappers.StudentMapper;
import edu.ucla.library.libservices.registrar.util.db.DataSourceFactory;
import edu.ucla.library.libservices.registrar.util.quarters.QuarterSetter;
import edu.ucla.library.libservices.registrar.util.strings.StringModder;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class StudentLoader
{
  private static final String SRDB_SELECT = 
    "exec Rs_V022 @TERM_PARA1=?, @TERM_PARA2=?";
  private static final String LIB_RESIDENT = "exec lib_resident @uid=?";
  private static Properties srdbProps;
  private static String term1;
  private static String term2;
  private static String propsFilename;
  private static DriverManagerDataSource srdb;

  public StudentLoader()
  {
  }

  public static void main( String[] args )
    throws IOException
  {
    BufferedWriter writer;
    List<StudentBean> students;
    Set<String> processed;
    StringModder modder;

    switch ( args.length )
    {
      case 1:
        propsFilename = args[ 0 ];
        break;
      default:
        System.err.println( "Usage: StudentLoader propsfilename" );
        System.exit( 1 );
    }
    
    //propsFilename = "C:\\Temp\\patrons\\srdb.props";
    processed = new TreeSet<String>();
    
    System.out.println("Started @ " + new SimpleDateFormat( "MM/dd/yy HH:mm.ss" ).format( new Date() ));

    loadProperties();
    setQuarterParams();

    modder = new StringModder();

    srdb = DataSourceFactory.getDataSource( srdbProps );

    writer = 
        new BufferedWriter( new FileWriter( srdbProps.getProperty( "registrar.output" ) ) );

    students = new JdbcTemplate( srdb ).query( SRDB_SELECT, new Object[]
          { term1, term2 }, new StudentMapper() );

    for ( StudentBean theStudent: students )
    {
      if ( ! processed.contains( theStudent.getStu_id() ) )
      {
        processed.add(theStudent.getStu_id());
        writer.write( modder.adjustStringLength( theStudent.getStu_id(), 
                                                 Integer.parseInt( srdbProps.getProperty( "UNIVERSITY_ID" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_addr_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PADDTYPE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_adr_priv_flag(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PADDPRIV" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_street_1(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PADDR1" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_street_2(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PADDR2" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_city_name(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PCITY" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_state_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PSTATE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_zip_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PZIP" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_prov_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PPROV" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_cntry7(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PCOUNTRY" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_pho_priv_flag(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PPHONEPRI" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getP_phone_no(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PPHONE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_addr_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TADDTYPE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_adr_priv_flag(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TADDPRIV" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_street_1(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TADDR1" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_street_2(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TADDR2" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_city_name(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TCITY" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_state_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TSTATE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_zip_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TZIP" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_prov_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TPROV" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_cntry7(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TCOUNTRY" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_pho_priv_flag(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TPHONEPRI" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getM_phone_no(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TPHONE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getStu_nm(), 
                                                 Integer.parseInt( srdbProps.getProperty( "NAME" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getStu_id2(), 
                                                 Integer.parseInt( srdbProps.getProperty( "STUD_NOB" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getSsn_mask(), 
                                                 Integer.parseInt( srdbProps.getProperty( "SSN" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getBirthdt(), 
                                                 Integer.parseInt( srdbProps.getProperty( "BIRTHDAT" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getSex(), 
                                                 Integer.parseInt( srdbProps.getProperty( "SEX" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getVisa_trm(), 
                                                 Integer.parseInt( srdbProps.getProperty( "VISA" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getCit_cntry_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "CNTRYCZ" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "STUDDIR" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getDeg_exp_term(), 
                                                 Integer.parseInt( srdbProps.getProperty( "DEGEXP" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getPerm_dis_blnk(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PERMDIS" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getTemp_dis_blnk(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TEMPDIS" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getReg_status(), 
                                                 Integer.parseInt( srdbProps.getProperty( "REGSTAT" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getBar_asmt_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "SBARCALC" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getPay_stat(), 
                                                 Integer.parseInt( srdbProps.getProperty( "PAYSTAT" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getWith_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "WITHDRAW" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getWith_dt(), 
                                                 Integer.parseInt( srdbProps.getProperty( "WITHDATE" ) ) ) );
        setCareerClass( theStudent, writer, modder );
        writer.write( modder.adjustStringLength( theStudent.getColl_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "COLL1" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getDeg_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "DEG1" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getMajor_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "MAJOR1" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getMajor_abbr(), 
                                                 Integer.parseInt( srdbProps.getProperty( "MAJOR1NM" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getSr_dept_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "DEPT1" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getSr_div_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "DIV1" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MAJOR2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MAJOR2NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "DEPT2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "DIV2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MAJOR3" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MAJOR3NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "DEPT3" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "DIV3" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MI1CO1" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MI1C1NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MINDP1NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MINDIV1" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MIN1CO2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MI1C2NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MINDP2NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MINDIV2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "COLL2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MJ1CO2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MJ1C2NM" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MJ1CO2DP" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "MJ1CO2DI" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getGrad_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "GRADCD" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getReg_typ_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "REGTYPE" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getFerpa(), 
                                                 Integer.parseInt( srdbProps.getProperty( "FERPA" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "AS1" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "AS2" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "AF1" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "AF2" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getEmail_privacy_flag(), 
                                                 Integer.parseInt( srdbProps.getProperty( "EMAILPR" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getEmail_upd_dte(), 
                                                 Integer.parseInt( srdbProps.getProperty( "EMAILUPD" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getSs_email_addr(), 
                                                 Integer.parseInt( srdbProps.getProperty( "EMAILADD" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "RT339" ) ) ) );
        writer.write( determineHonors( theStudent.getSp_pgm2(), modder ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "RT388" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "RT377" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "RT378" ) ) ) );
        writer.write( modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "RT202" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getTerm_cd(), 
                                                 Integer.parseInt( srdbProps.getProperty( "TERM" ) ) ) );
        writer.write( modder.adjustStringLength( theStudent.getEmail_return_flag(), 
                                                 Integer.parseInt( srdbProps.getProperty( "EMAILFLG" ) ) ) );
        writer.newLine();
      }
    }

    writer.flush();
    writer.close();

    System.out.println("Finished @ " + new SimpleDateFormat( "MM/dd/yy HH:mm.ss" ).format( new Date() ));
  }

  private static void loadProperties()
  {
    srdbProps = new Properties();
    try
    {
      srdbProps.load( new FileInputStream( propsFilename ) );
    }
    catch ( IOException ioe )
    {
      System.err.println( "Unable to open properties file: " + 
                          propsFilename );
      ioe.printStackTrace();
      System.exit( 1 );
    }
  }

  private static String determineHonors( String value, 
                                         StringModder modder )
  {
    return ( value.equalsIgnoreCase( "Y" )? "HON" : 
             modder.getBlanks( Integer.parseInt( srdbProps.getProperty( "RT340" ) ) ) );
  }

  private static void setQuarterParams()
  {
    QuarterSetter quarters;
    quarters = new QuarterSetter();

    term1 = quarters.getTerm1();
    term2 = quarters.getTerm2();
  }

  private static void setCareerClass( StudentBean student, 
                                      BufferedWriter writer, 
                                      StringModder modder )
    throws IOException
  {
    JdbcTemplate checkPostDoc = new JdbcTemplate( srdb );
    int isPostDoc = checkPostDoc.queryForInt( LIB_RESIDENT, new Object[]
        { student.getStu_id() } );
    if ( isPostDoc == 1 )
    {
      writer.write( "P" );
      writer.write( "PR " );
    }
    else
    {
      writer.write( modder.adjustStringLength( student.getCareer(), 
                                               Integer.parseInt( srdbProps.getProperty( "CAREER" ) ) ) );
      writer.write( modder.adjustStringLength( student.getStu_class(), 
                                               Integer.parseInt( srdbProps.getProperty( "CLASS" ) ) ) );
    }
  }
}
