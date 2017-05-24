import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class test extends Survey {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<RCA> correctResponse = new Vector<RCA>();
	protected OuputConsole O = new OuputConsole();
	
	
	public test() {
		
	}
	/*Prompts the user to type in the name of the survey 
	 * and sets the Test name to the users input and calls the QuestionMenu */
	public test SetTest() {
		
		System.out.println("type name of test");
		Scanner sc = new Scanner(System.in);  
		String name= sc.nextLine();
		Survey= name;
		QuestionMenu();
		sc.close();
		return this;
		
	}
	/*Question Menu is called and the user is prompted 
	 * to pick a question or quit. Then the question object is created. */
	public void QuestionMenu(){
		
		System.out.println("1) Add a new T/F question ");
		System.out.println("2) Add a new multiple choice question ");
		System.out.println("3) Add a new short answer question ");
		System.out.println("4) Add a new essay question ");
		System.out.println("5) Add a new matching question ");
		System.out.println("6) Add a new ranking question ");
		System.out.println("7) back ");

		System.out.println("Select One option from above ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			   System.out.println("int, please!");
			   sc.nextLine();
		}
			
		//User input for the question is used to generate the question and adds to question vector and RCA
		try{
			Question Question = null; 
			RCA answer = new RCA();
			int i = sc.nextInt();
			int WL = 0;
			Vector<String> right = null; 
			Vector<String> left = null;
			
			switch(i) {
			
			case 1: 				   
				   System.out.println("T/F:");
				   Question = new TrueFalse();
				   Question.setQuestion();   
				   break;
			case 2: 
				   System.out.println("MC ");
				   Question = new MC();
				   Question.setQuestion(); 
				   right = ((MC) Question).getChoices();
				   break;
			case 3: 
				    System.out.println("SA ");
				    Question = new SA(); 
				    Question.setQuestion();
				    WL = ((SA) Question).getWordlimit();
					break;
			case 4: 
				    System.out.println("Essay");
				    Question = new Essay(); 
				    Question.setQuestion();
					break;
			case 5: 
					System.out.println("Matching");
					Question = new Matching(); 
					Question.setQuestion();
					right = ((Matching) Question).getRightList();
					left = ((Matching) Question).geLeftList();
					break;
			case 6: 
				    System.out.println("Ranking");
				    Question = new Ranking(); 
				    Question.setQuestion();
					right = ((Ranking) Question).getRightList();
				    break;
			case 7: 
				super.TestMenu();
			    break;

	        default:
				System.out.println("Error Please select a valid option ");
				QuestionMenu();
	        }
			questions.add(Question);
			answer.addResponse(i,right,WL,left);
			correctResponse.addElement(answer);
			QuestionMenu();
		}
		catch(Exception e){
			System.out.println("Error Please select a valid option");
			QuestionMenu();
		}

	
	}
	
	/*Display the Test to the console*/ 
	public void displaySurvey(){
		
		System.out.println("Displaying the test with the questions and respected answers \n");
		this.O.display("Name of the test - "+this.Survey+"\n");
		
		for( int i =0; i<questions.size(); i++){
			questions.get(i).display(this.O);
			this.O.display("The correct answer \n");
			correctResponse.get(i).display(this.O);
		}
		this.O.display("\n");
	}
	/*Set the vector RCA*/
	public void setCorrectResponse(Vector<RCA> correctResponse) {
		this.correctResponse = correctResponse;
		}
	/*Returns the vector RCA*/
	public Vector<RCA> getCorrectResponse() {
		
		return this.correctResponse;
		}
	//Modify the questions and the correct answers 
	@SuppressWarnings("resource")
	public void modify(){
		
		NewTest.displaySurvey();
		System.out.println("What question do you wish to modify?");
		
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			   System.out.println("int, please!");
			   sc.nextLine();
			}
		
		try
		{
			
			int i = sc.nextInt();
			
			Question question = this.questions.get((i-1));
			RCA correctResponse = this.correctResponse.get((i-1));
			question.Modify();
			
			
			System.out.println("Do you wish to modify the correct answer?");
			Scanner sc1 = new Scanner(System.in);
			
			
			String prompt1 = sc1.nextLine();
			if (prompt1.toLowerCase().equals("yes"))
			{
				correctResponse.Modify(question,correctResponse);
			}
		}
		
		catch(Exception e){
			System.out.println("Error has accured while Modifing Please rerun Modification");
			QuestionMenu();
		}

		
	
	}

	
	@SuppressWarnings("resource")
	public void gradeTest(Survey currentSurvey) throws IOException {
		
		int grade=0 , total=0,  essays=0;
		
		
		Vector<RCA> User = new Vector<RCA>();
		
		Set<String> keys = currentSurvey.response.keySet();
		Scanner sc = new Scanner(System.in);
		String Name;
		
		O.display("whos test do you want to grade \n");
		for(String key: keys)
		{
			System.out.println(key);
		}
		
		Name = sc.nextLine();
		User = response.get((Name));

		for(int i=0;i<correctResponse.size();i++)
		{
			if(Essay.class.equals(questions.get(i).getClass()))
			{
				essays=essays+10;	
			}
			else if(correctResponse.get(i).check(User.get(i)))
			{
				grade=grade+10;	
			}
			total=total+10;
		}
		total = total - essays;
		O.display(Name + " grade is "+grade+"/"+total +"\n");
	}
	
}
