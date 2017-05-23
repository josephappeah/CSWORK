// intArgs.java - Reads command-line args, converts to integers
// Demonstrates:	Integer, argv, String
//
// 3/04 
//
//


import java.io.*;
import javax.management.*;

public class intArgs
{
	/** @author Kurt Schmidt
	 *  @param argv - array of integer strings
	 */

	public static void main( String[] argv ) 
	{
		int l = argv.length;
		// this is a line comment

		try {
			for( int i = 0; i< l; ++i ) {
				int n = Integer.parseInt( argv[i] );
				System.out.println( n );
			}
		}
		catch( NumberFormatException e ) {
			System.out.println( "Caught a NumberFormatException!:" );
			System.out.println( e );
		}
		catch( Exception e ) {
			System.out.println( "Caught a generic Exception!:" );
			System.out.println( e );
		}
		finally {
			System.out.println( "We're in the finally block!" );
		}

	} // main

} 	// class intArgs


