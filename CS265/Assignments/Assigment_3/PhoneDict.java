/* 
Sunny Shah 
CS265-005
Assignment 3: PhoneDict 
Fri Nov 20 10:51:31 EST 2015
Input:words
*/
//Import java statements
import java.io.FileReader ;
import java.io.IOException ;
import java.util.Scanner ;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class PhoneDict
{
	//Main catchs I/O exceptions
	public static void main( String[] argv) throws IOException
	{
		String fileName = "words";
		String l = "";
		//read in file words wrap with bufferedReader
		FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
		HashMap<String, String> hmap = new HashMap<String, String>();
		
		//while there is a line to read in readline()
        while((l = bufferedReader.readLine()) != null) 
		{
				String s = l;
				String Number = "";
				//assign numbers to each word in each line if the file
				for (int i = 0; i < s.length(); i++)
				{	
					
					char c = s.charAt(i);
					if (Character.toString(c).matches("[a-c]") || Character.toString(c).matches("[A-C]"))
					{
						Number = Number + "2"; 
					}
					else if (Character.toString(c).matches("[d-f]") || Character.toString(c).matches("[D-F]"))
					{
						Number = Number + "3"; 
					}
					else if (Character.toString(c).matches("[g-i]") || Character.toString(c).matches("[G-I]"))
					{
						Number = Number + "4"; 
					}
					else if (Character.toString(c).matches("[j-l]")	 || Character.toString(c).matches("[J-L]"))
					{
						Number = Number + "5"; 
					}
					else if (Character.toString(c).matches("[m-o]") || Character.toString(c).matches("[M-O]"))
					{
						Number = Number + "6"; 
					}
					else if (Character.toString(c).matches("[p-s]") || Character.toString(c).matches("[P-S]"))
					{
						Number = Number + "7"; 
					}
					else if (Character.toString(c).matches("[t-v]") || Character.toString(c).matches("[T-V]"))
					{
						Number = Number + "8"; 
					}
					else if (Character.toString(c).matches("[w-z]") || Character.toString(c).matches("[W-Z]"))
					{
						Number = Number + "9"; 
					}
				}
				
				//set hash table of words and coresponding number
				if (hmap.get(Number) == null) 
				{
					hmap.put(Number,l);
		
				} 
				else 
				{
					String val = hmap.get(Number);
					val=val.replaceAll("[()]", "");
					String NewVal = "("+val+ "|" +l+")";
					hmap.put(Number,NewVal);
				} 
                
        }


		
		Scanner src ;
		src = new Scanner( System.in ) ;
		String line ;
		//read in user input and output coresponding words 
		while( src.hasNext() )
		{
			line = src.nextLine();
			line=line.replaceAll("0*$", "");
			line=line.replaceFirst("^0+(?!$)", "");
			line=line.replaceAll("(0)+", "0");
		

  			String delims = "[0]";
			String[] tokens = line.split(delims);
			int len = tokens.length;
 			for (int i = 0; i < tokens.length; i++){
				if(i == 0 && hmap.get(tokens[i]) != null ){
					System.out.print(hmap.get(tokens[i]));
				}
				else if(hmap.get(tokens[i])!= null){
					System.out.print(" "+hmap.get(tokens[i]));
				}
				if(hmap.get(tokens[i])== null){
					if(i != 0 ){
						System.out.print(" ");
					}
					for(int j = 0; j < tokens[i].length(); j++){
					
							System.out.print("*");
						
					}
					
				}

			}   
			System.out.println();

		}



 


		
	}
	
}