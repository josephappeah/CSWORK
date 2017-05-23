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

import java.io.FileReader ;
import java.io.IOException ;
import java.util.Scanner ;

public class test
{
	public static void main( String [] argv ) throws IOException
	{
		Scanner src ;

		if( argv.length == 0 )  // read from stdin
			src = new Scanner( System.in ) ;
		else
		{
			FileReader fin = new FileReader( argv[0] ) ;
			src = new Scanner( fin ) ;
				// Can wrap a File, InputStream, or String
			// FileReader fin = new Scanner( new FileReader( argv[0] )) ; 
		}

		String line ;

		while( src.hasNext() )
		{
			line = src.nextLine();

			System.out.println( "read: " + line );
		}
	}
}

