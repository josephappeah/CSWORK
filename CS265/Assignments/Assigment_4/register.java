/* 
Sunny Shah 
CS265-005
Assignment 4: assn4 
Wed Nov 25 22:02:23 EST 2015
main class contains logic for register operation
*/

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;

public class register {
	
	public  int ones;
	public  int fives;
    public  int tens;
    public  int twenties;
    public  int total;
    public  int sales;
	
	//consttructor that initiates varaibles 
	public register(){
		ones=0;
		fives=0;
		tens=0;
		twenties=0;
		total=0;
		sales=0;
	}
	//init methhod that inits the register with given amounts and writes it to regLog 
	public void init(int tot ,int a, int b, int c, int d) throws IOException{
		try{
			int test = a*1 + b*5 + c*10 + d*20;
			total = tot; 
			ones = a;
			fives = b;
			tens = c;
			twenties = d;
			if(test != total){
			System.exit(2);
			}
			PrintWriter writer = new PrintWriter("regLog", "UTF-8");
			writer.println(sales+" "+":"+" "+total+" "+"="+" "+ones+" "+fives+" "+tens+" "+twenties);
			writer.close();
		}
		catch(IOException ex){
			System.exit(4);
		}
	}
	
	//purchase method which takes in the price and given bills and outputs change to the user 
	public void purchase(int price ,int a, int b, int c, int d) throws IOException{
		try{
			int amt_tender = a*1 + b*5 + c*10 + d*20;
			int change =amt_tender - price;
			sales += price;
			if(change < 0){
				System.exit(2);
			}
			int o = 0;
			int f = 0;
			int t = 0;
			int tw = 0;
			int q=0;
			int w=0;
			int e=0;
			int r=0;
			int s=0;
			String l = "";
			FileReader fileReader = new FileReader("regLog");
			BufferedReader regLog = new BufferedReader(fileReader);
			while((l = regLog.readLine()) != null){
				String[] log = l.split("\\s+");
				 s = sales + Integer.parseInt(log[0]);
				 q = Integer.parseInt(log[4]);
				 w = Integer.parseInt(log[5]);
				 e = Integer.parseInt(log[6]);
				 r = Integer.parseInt(log[7]);
			}
			 while(change >= 0)
				if(change >= 20 && r > 0){
					change -= 20;
					r -= 1;
					tw += 1;
				}
				else if(change >= 10 && e > 0){
					change -= 10;
					e -= 1;
					t += 1;
				}
				else if(change >= 5 && w > 0){
					change -= 5;
					w -= 1;
					f += 1;
				}
				else if(change > 0 && q > 0){
					change -= 1;
					q -= 1; 
					o += 1;
				}
				else if(change > 0 && q <= 0){
					System.exit(3);                
				}
				else if(change == 0){
					System.out.println(o + " " + f + " " + t + " " + tw);
					PrintWriter writer = new PrintWriter("regLog", "UTF-8");
					total += q+a + (w+b)*5 + (e+c)*10 + (r+d)*20; 
					writer.println(s+" "+":"+" "+total+" "+"="+" "+(q+a)+" "+(w+b)+" "+(e+c)+" "+(r+d));
					writer.close();
					break;
				}
		}
		catch(IOException ex){
			System.exit(4);
		}
	}
	//change method that takes in the tendered amount and bills. hanges the bill count in the reg. outputs the change 
	public void change(int[] tendered ,int[] bills_requested) throws IOException{
		try {		
			int bills_req = bills_requested[0]*1 + bills_requested[1]*5 + bills_requested[2]*10 + bills_requested[3]*20;
			int ten = tendered[0]*1 + tendered[1]*5 + tendered[2]*10 + tendered[3]*20;
			int change = bills_req - ten;
			if(change < 0 || bills_req != ten){
				System.exit(2);
			}		
			int q=0;
			int w=0;
			int e=0;
			int r=0;
			int s=0;
			String l = "";

			FileReader fileReader = new FileReader("regLog");
			BufferedReader regLog = new BufferedReader(fileReader);
			while((l = regLog.readLine()) != null){
				String[] log = l.split("\\s+");
				 s = sales + Integer.parseInt(log[0]);
				 q = Integer.parseInt(log[4]);
				 w = Integer.parseInt(log[5]);
				 e = Integer.parseInt(log[6]);
				 r = Integer.parseInt(log[7]);
			}
				q += tendered[0];
				w += tendered[1];
				e += tendered[2];
				r += tendered[3];
				
				
				q -= bills_requested[0];
				w -= bills_requested[1];
				e -= bills_requested[2];
				r -= bills_requested[3];
			if(q < 0 || w < 0 || e < 0 || e < 0 || r < 0){
				System.exit(3);
			}			
					PrintWriter writer = new PrintWriter("regLog", "UTF-8");
					total += q + w*5 + e*10 + r*20; 
					writer.println(s+" "+":"+" "+total+" "+"="+" "+q+" "+w+" "+e+" "+r);
					writer.close();
					System.out.println(bills_requested[0]+" "+bills_requested[1]+" "+bills_requested[2]+" "+bills_requested[3]);
		}
		catch(IOException ex){
			System.exit(4);
		}
		
	}
	
	//report method reads and outputs the regLog file that holds the reg data 
	public void report() throws IOException {
		String l = "";
		try{
			FileReader fileReader = new FileReader("regLog");
			BufferedReader regLog = new BufferedReader(fileReader);
			
			while((l = regLog.readLine()) != null){
				System.out.println(l);
			}
		}

		catch(IOException ex){
			System.exit(4);
		}
		
	}
	
	//main
	public static void main( String[] args ) throws IOException {

		register reg = new register();
		String l = "";
		String S = "";
		String flag = "";
		String rhs = "";
		String lhs = "";
		if(args.length == 0){
			System.exit(1);
		}
		if(args[0].equals("report")){
			if(args.length > 1)
			{
				System.exit(1);
			}
			reg.report();
			System.exit(0); 
		}
  
 		if(args[0].matches("change")){
			
				if(args.length < 7){
					for(int i = 1; i < args.length -1; ++i){
						 S = S +" " + args[i];
						}
					if(!S.contains("=")){
					System.out.println("1:Bad arguments (format/number) 184");
					System.exit(1);
					}
				}
				if(args.length > 10){
					System.out.println("1:Bad arguments (format/number) 184");
					System.exit(1);
				}
			 			
  
		} 
		
 		if(args[0].matches("purchase|init")){
			if(args.length < 4 || args.length > 10 ){
				System.exit(1); 			
			}			
			if(!args[2].matches("=")){
				System.exit(1); 
			}   
		} 		
 		if(!args[0].matches("init|report|change|purchase")){
			System.exit(1); 
		}
		
		for( int i = 0; i< args.length; ++i )
			if(i == 0){
			 l = args[0];
			}
			else{
			 l = l +" " + args[i];
			}
			
		System.out.println(l);
		String[] toks = l.split( "=" );
			for( int i = 0; i< toks.length; ++i )
			{
				 lhs = toks[0];
				 rhs = toks[1];

			}
 		if (!lhs.matches(".*\\d+.*")  || !rhs.matches(".*\\d+.*")){
				System.exit(1);			
		} 
			

			rhs=rhs.replaceFirst("\\s+", "");
			String[] tmp1 = rhs.split( "\\s+" );
			String[] tmp2 = lhs.split( "\\s+" );
			int[] rhsArray = new int[5];
			int[] lhsArray = new int[5];
			for( int i = 0; i< tmp1.length; ++i )
			{
				rhsArray[i] = Integer.parseInt(tmp1[i]);

			}
			for( int i = 0; i< tmp2.length; ++i )
			{
				if(i != 0)
					lhsArray[i-1] = Integer.parseInt(tmp2[i]);

				
			}

			if(tmp2[0].equals("init")){
				reg.init(lhsArray[0],rhsArray[0],rhsArray[1],rhsArray[2],rhsArray[3]);
			}
			if(tmp2[0].equals("purchase")){
				reg.purchase(lhsArray[0],rhsArray[0],rhsArray[1],rhsArray[2],rhsArray[3]);
			}
			if(tmp2[0].equals("change")){
				reg.change(lhsArray,rhsArray);
			}

	}

	
}
