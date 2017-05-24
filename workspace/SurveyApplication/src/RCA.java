import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Vector;

public class RCA implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Vector<String> RCA=new Vector<String>();

	public RCA(){
		
	}
	/*This places the responses in the vector of strings for each type of question*/
	public void addResponse(int option, Vector<String> right, int wL, Vector<String> left){
		try{
		switch(option){
		case 1:
			
			System.out.println("Enter Correct answer T/F:");
			Scanner TF = new Scanner(System.in);  
			String answer= TF.nextLine();
			
			if(!(answer.equals("T") || answer.equals("F")) ){
				System.out.println("Error Please enter T or F");
				addResponse(option, right, wL, left);	
			}
			RCA.addElement(answer);
			break;

		case 2: 
			
			System.out.println("Enter number of correct answers for the multiple choice question ");
			Scanner MC = new Scanner(System.in);
			while (!MC.hasNextInt()) {
				   System.out.println("int, please!");
				   MC.nextLine();
				}
			int n1 = MC.nextInt();
			System.out.println("Please enter the correct response ");
			Scanner MCR = new Scanner(System.in);
			for (int i =0; i < n1; i ++ ){
				System.out.print((i+1)+") ");
				String MCanswer= MCR.nextLine();
				
				RCA.addElement((i+1)+") "+MCanswer);
			}
			break;
			
		case 3: 
			System.out.println("Please enter the correct response in the correct short answer. World length in characters is: "+ wL);
			Scanner SAR = new Scanner(System.in);
			String SAA= SAR.nextLine();
				
			if(SAA.length() > wL){
				System.out.println("Answer is longer then word length. Reenter the Answer. World length in characters is: "+ wL);
				addResponse(option, right, wL, left);
			}
			RCA.addElement(SAA);
			
			break;
			
		case 5:
			System.out.println("Please enter the correct match ex: Matching a Matching b  ");
			Scanner MatchR = new Scanner(System.in);
			for (int i =0; i < right.size(); i ++ ){
				System.out.print((i+1)+") "+ right.get(i)+" matches with: " );
				String Matchanswer= MatchR.nextLine();
				if(!left.contains(Matchanswer)) {
					System.out.println("please enter a valid match from the choices provided"); 
					addResponse(option, right, wL, left); 
					
				}
				//RCA.addElement((i+1)+") "+Matchanswer);
				RCA.addElement(Matchanswer);
			}			
			break;

		case 6:
			
				System.out.println("Please enter the correct response in the correct ranking order ");
				Scanner RankR = new Scanner(System.in);
				
				for (int i =1; i < right.size()+1; i++ ){
					System.out.println(i);
					String Rankanswer= RankR.nextLine();
					
					if(!right.contains(Rankanswer)) {
						System.out.println("please enter a valid match from the choices provided"); 
						i=0;
						addResponse(option, right, wL, left); 
						
					}
					RCA.addElement(Rankanswer);
				}
		
				break;
			
		default:
			String input;
			input = "Not applicable in essay type of questions ";
			RCA.addElement(input);
			break;
		}
		}
		catch(Exception e){
			System.out.println("Error Please select a valid option");
			addResponse(option, right, wL, left);
		}
		
	}

	public void setRCA(Vector<String> rca){
		RCA = rca;
	}
	public Vector<String> getRCA(){
		return RCA;
		
	}
	@SuppressWarnings("resource")
	public void setResponse(Question question) throws NumberFormatException, IOException{
		
		if(TrueFalse.class.equals(question.getClass())){
			
			System.out.println("Please enter your response. Write True and False only T/F:");
			Scanner TF = new Scanner(System.in);  
			String answer= TF.nextLine();
			
			if(!(answer.equals("T") || answer.equals("F")) ){
				System.out.println("Error Please enter T or F");
				setResponse(question);
			}
			RCA.addElement(answer);
		}
		else if(Essay.class.equals(question.getClass())){
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Please write your response. To exit Please hit Enter twice to register newline three times");
			String line;
			while(!(line = keyboard.nextLine()).isEmpty()) {
				  line = keyboard.nextLine();
				  RCA.addElement(line);
				}
				System.out.println("Thank You!");
		}
		else if(Matching.class.equals(question.getClass())){
		
			Vector<String> right = ((Matching) question).getRightList();
			Vector<String> left = ((Matching) question).geLeftList();
			
			System.out.println("Please enter the correct match ex: Matching a Matching b  ");
			Scanner MatchR = new Scanner(System.in);
			
			for (int i =0; i < right.size(); i ++ ){
				System.out.print((i+1)+") "+ right.get(i)+" matches with: " );
				
				String Matchanswer= MatchR.nextLine();
				if(!left.contains(Matchanswer)) {
					System.out.println("please enter a valid match from the choices provided"); 
					RCA.clear();
					i=0;
					setResponse(question);
					
				}
				RCA.addElement(Matchanswer);
			}
		}
		else if(Ranking.class.equals(question.getClass())){

				
				Vector<String> right = ((Ranking) question).getRightList();

				System.out.println("Please enter the correct response in the correct ranking order ");
				Scanner RankR = new Scanner(System.in);
				
				for (int i =1; i < right.size()+1; i++ ){
					System.out.print((i)+") ");
					String Rankanswer= RankR.nextLine();
					System.out.println(right);
					
					if(!right.contains(Rankanswer)) {
						System.out.println("please enter a valid match from the choices provided"); 
						RCA.clear();
						setResponse(question);
					}
					
					RCA.addElement(Rankanswer);
				}
				
		}
		else if(MC.class.equals(question.getClass())){
				
				BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));

				MC mcq = (MC) question;
				System.out.println("You are only permitted to enter up to "+ mcq.choices.size()+" response." );
				

				System.out.println("Enter number of correct answers you think are right");
				Scanner sc = new Scanner(System.in);
				while (!sc.hasNextInt()) {
					   System.out.println("int, please!");
					   sc.nextLine();
				}
				int k = sc.nextInt();
				if(mcq.choices.size()<k){
					System.out.println("You can not enter response more than options provided. Try again.");
					setResponse(question);

				}
				else {
					if(k>1){
						System.out.println("Please write "+k+" responses. Write options number only ");
						
						for(int i=0;i<k;i++){
							String input;
							try {
								System.out.print((i+1)+") ");
								input = br3.readLine();
								if(!mcq.choices.contains(input)) {
									System.out.println("please enter a valid match from the choices provided"); 
									RCA.clear();
									setResponse(question);
									
								}
								RCA.addElement(input);
							}
							catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					
					else{
						System.out.println("Please write one response only. Write options number only ");
						String input;
						try {
							input = br3.readLine();
							RCA.addElement(input);
						} 
						catch (IOException e) {
							e.printStackTrace();
						}	
					}

				}


			}
		else if(SA.class.equals(question.getClass())){

				int WL = ((SA) question).getWordlimit();
		
				System.out.println("Please enter your response to the correct short answer. World length in characters is: "+ WL);
				Scanner SAR = new Scanner(System.in);
				String SAA= SAR.nextLine();
					
				if(SAA.length() > WL){
					System.out.println("Answer is longer then word length. Reenter the Answer. World length in characters is: "+ WL);
					setResponse(question);
				}
				RCA.addElement(SAA);
			}
		

	}
	
	public void display(Output o) {
	for(int i=0;i<RCA.size();i++){
		o.display(RCA.elementAt(i)+" ");
			}
		o.display("\n");
		}
	
	
	//need to fix how to handle some issues with code 
	@SuppressWarnings("resource")
	public void Modify(Question question, RCA correctResponse) {
		// TODO Auto-generated method stub
		
		if(TrueFalse.class.equals(question.getClass())){
			
			System.out.println("What choice do you wish to make the new correct answer T/F:");
			Scanner TF = new Scanner(System.in);  
			String answer= TF.nextLine();
			
			if(!(answer.equals("T") || answer.equals("F")) ){
				System.out.println("Error Please enter T or F");
				Modify(question,correctResponse);	
			}
			correctResponse.RCA.set(0,answer);
		}
		else if(MC.class.equals(question.getClass())){
			

				//fix this to catch in correct inputs 
				if(correctResponse.RCA.size()>1){
					System.out.println("What choice do you wish to make the new correct answer? Please enter "+ correctResponse.RCA.size() + " answers");
					Scanner MCR = new Scanner(System.in);
					for (int i =0; i < correctResponse.RCA.size(); i ++ ){
						System.out.print((i+1)+") ");
						String MCanswer= MCR.nextLine();
						
						correctResponse.RCA.set(i,(i+1)+") "+MCanswer);
					}
				}
				else{
					System.out.println("What choice do you wish to make the new correct answer?");
					
					Scanner MCR = new Scanner(System.in);
					String MCanswer= MCR.nextLine();
					correctResponse.RCA.set(0,(0+1)+") "+MCanswer);
				}

		}
		else if(SA.class.equals(question.getClass())){
			
			 int WL = ((SA) question).getWordlimit();
			System.out.println("Please enter the correct response in the correct short answer. World length in characters is: "+ WL);
			Scanner SAR = new Scanner(System.in);
			String SAA= SAR.nextLine();
				
			if(SAA.length() > WL){
				System.out.println("Answer is longer then word length. Reenter the Answer. World length in characters is: "+ WL);
				Modify(question,correctResponse);
			}
			correctResponse.RCA.set(0,SAA);
		}
		else if(Ranking.class.equals(question.getClass())){
			//buggy need to fix goes over the limit everytime it errors and saves that value 
			System.out.println("Please re-enter the correct response in the correct ranking order ");
			Scanner RankR = new Scanner(System.in);
			int i;
			Vector<String> right = ((Ranking) question).getRightList();
			
			for ( i =1; i < right.size()+1; i++ ){
				System.out.println(i);
				String Rankanswer= RankR.nextLine();
				
				if(!correctResponse.RCA.contains(Rankanswer)) 
				{
					
					System.out.println("please enter a valid match from the choices provided"); 
					i=0;
					Modify(question,correctResponse);
					
				}
				correctResponse.RCA.set(i--,Rankanswer);
			}
		}
		else if(Matching.class.equals(question.getClass())){
			//rough going to be bugy 
			System.out.println("Please enter the correct match ex: Matching a Matching b  ");
			Scanner MatchR = new Scanner(System.in);
			Vector<String> right = ((Matching) question).getRightList();
			for (int i =0; i < right.size(); i ++ ){
				System.out.print((i+1)+") "+ correctResponse.RCA.get(i)+" matches with: " );
				String Matchanswer= MatchR.nextLine();
				if(!correctResponse.RCA.contains(Matchanswer)) {
					System.out.println("please enter a valid match from the choices provided"); 
				
					
				}
				correctResponse.RCA.addElement((i+1)+") "+Matchanswer);
			}
		}
		
		else{
			System.out.println("Correct answers not to be modified");
		}
		
		
	}

	public boolean check(RCA rca2) {
		// TODO Auto-generated method stub
		if(this.RCA.equals(rca2.RCA))
			return true;
		else
			return false;

	}
	
}
