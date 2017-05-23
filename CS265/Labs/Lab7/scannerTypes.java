// fileIoRecords.java - Reading diskfile, parsing by line
// Demonstrates:	Scanner, FileReader
//
// Kurt Schmidt
// 11/11
// 
// EDITOR:  tabstop=2, cols=80
//
// java version "1.6.0_26" on
// Linux 2.6.35-30-generic #61-Ubuntu SMP Tue Oct 11 17:52:57 UTC 2011 
//

// Scanner is a bit of a Swiss-army knife.  Lots of useful stuff.  See the
// documentation.

import java.io.FileReader ;
import java.io.IOException ;
import java.util.Scanner ;

public class scannerTypes
{
	public static void main( String [] argv ) throws IOException
	{
		int i;
		double d;
		String s;

		Scanner src ;

		if( argv.length == 0 )  // read from stdin
			src = new Scanner( System.in ) ;
		else
		{
			FileReader fin = new FileReader( argv[0] ) ;
			src = new Scanner( fin ) ;
				// Can wrap a File, InputStream, or String
		}

		while( src.hasNext() )
		{
			if( src.hasNextDouble() ) {
				d = src.nextDouble() ;
				System.out.println( "double: " + d ) ; }
			else if( src.hasNextInt() ) {
				i = src.nextInt() ;
				System.out.println( "int: " + i ) ; }
			else {	// just grab next token as a string
				s = src.next() ;
				System.out.println( "string: " + s ) ; }
		}
	}
}

