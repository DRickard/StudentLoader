package edu.ucla.library.libservices.registrar.util.strings;

public class StringModder
{
  private static final char SPACE = 040;
  private char[] spaces;

  public StringModder()
  {
    spaces = new char[60];
    for ( int i = 0; i < 60; i++ )
      spaces[i] = SPACE;
  }

  public String adjustStringLength(String data, int fieldLength)
  {
    StringBuffer buffer;
    
    if ( data != null )
      buffer = new StringBuffer(data);
    else
      buffer = new StringBuffer();

    if ( buffer.length() > fieldLength )
      buffer = new StringBuffer( buffer.substring( 0, fieldLength ) );
    else if ( buffer.length() < fieldLength )
      buffer.append(spaces, 0, fieldLength - buffer.length() );
    
    return buffer.toString();
  }

  public String getBlanks(int length)
  {
    return new String(spaces, 0, length);
  }
  
}
