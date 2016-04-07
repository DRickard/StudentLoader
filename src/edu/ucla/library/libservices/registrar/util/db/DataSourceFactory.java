package edu.ucla.library.libservices.registrar.util.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Properties;

public class DataSourceFactory
{
  public DataSourceFactory()
  {
  }

  public static DriverManagerDataSource getDataSource( Properties props )
  {
    DriverManagerDataSource db;

    db = new DriverManagerDataSource();
    db.setDriverClassName( props.getProperty( "srdb.driver" ) );
    db.setUrl( props.getProperty( "srdb.url" ) );
    db.setUsername( props.getProperty( "srdb.user" ) );
    db.setPassword( props.getProperty( "srdb.password" ) );

    return db;
  }
}
