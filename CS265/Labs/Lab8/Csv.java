import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.Scanner;


public class Csv {

    private String line;
	//public int count;
    private ArrayList<String> fld = null;
	private BufferedReader input;
		
	// read and parse comma-separated value
    public Csv(String file) throws IOException 
	{
          
		  FileReader fileReader = new FileReader(file);
		  input = new BufferedReader(fileReader);
		//Scanner src;

    }

	// getline: get one line, grow as needed
    public String getLine() throws IOException 
	{
		
            line = input.readLine();
            if (line == null)
            return line;

            fld = split(line);
            return line;
        
    }

	// split: split line into fields
    public String getfield(int n) throws IOException 
	{
   
		if (n < 0 || n >= getIndex())
			return "";
		else
			return (String)fld.get(n);
    }

	//returns the index of the pointer
    public int getIndex() throws IOException 
	{
        return fld.size();
    }

	//slits the feilds up b the comma delimiator and places the results into the array list for acces 
    private static ArrayList split(String line) throws IOException 
	{
       ArrayList list = new ArrayList();
       int i, j;

       if (line.length() == 0)
           return list;
       i = 0;

  
           do {
               if (i < line.length() && line.charAt(i) == '"') 
			   { 
                   StringBuffer fld = new StringBuffer();
                   j = advQuoted(line, ++i, fld);
                   list.add(fld.toString());
               } 
			   else 
			   { 
                   j = line.indexOf(",", i);
                   if (j == -1)
                       j = line.length();
                   list.add(line.substring(i, j));
               }
               i = j + 1;
           } while (j < line.length());

           return list;
  
    }

	// advquoted: quoted field index of quoted  
    private static int advQuoted(String s, int i, StringBuffer fld) throws IOException 
	{
        int j;
        for (j = i; j < s.length(); j++) 
		{
            if (s.charAt(j) == '"' && (++j == s.length() || s.charAt(j) != '"')) 
			{
                int k = s.indexOf(",", j);
                if (k == -1)
                    k = s.length();
                fld.append(s.substring(j, k));
                j = k;
                break;
            }
            fld.append(s.charAt(j));
        }
        return j;
    }
	//main method creates and prints 
    public static void main(String args[]) throws IOException
	{

        String line;
        String file = "test.csv"; 

        Csv csv = new Csv(file);

        while ((line = csv.getLine()) != null) 
		{
			System.out.println("input:"+line);
            for (int i = 0; i < csv.getIndex(); i++)
                System.out.println("field[" + i + "] =`"+csv.getfield(i)+"'");
				
        }
    }
}