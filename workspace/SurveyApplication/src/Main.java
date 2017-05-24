
import java.util.Scanner;

public class Main {
	
	static Survey NewSurvey;
	static test NewTest;
	
	public static void main(String[] args) {
		
		//Start the program and start the menu
		menu();
	}
	/*menu method prompting the user to pick a Survey or a Test*/
	public static void menu(){
		System.out.println("1) Survey ");
		System.out.println("2) Test ");
		System.out.println("Select One option from above ");
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			   System.out.println("int, please!");
			   sc.nextLine();
			}
		try{
			int i = sc.nextInt();

			switch(i) {
			case 1: 
					System.out.print("Survey Selected: \n");
					SurveyMenu();
					break;
			case 2: 
					System.out.print("Test Selected: \n");
					TestMenu();
					break;
	        default:
				System.out.print("Error Please select a valid option \n");
				menu();

	        }
		}
		catch(Exception e){
			System.out.print("Error Please select a valid option \n");
			menu();
		}
	}
	
	/*Survey Menu Prompt */
	public static void SurveyMenu(){
		
		System.out.println("1) Create a new Survey ");
		System.out.println("2) Display a Survey ");
		System.out.println("3) Load a Survey ");
		System.out.println("4) Save a Survey ");
		System.out.println("5) Modify an Existing Survey ");
		System.out.println("6) Take a Survey ");
		System.out.println("7) Tabulate a Survey ");
		System.out.println("8) Quit ");
		
		System.out.println("Select One option from above ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			   System.out.println("int, please!");
			   sc.nextLine();
			}
		
		try{
			int i = sc.nextInt();
			String T = "Survey";
			switch(i) {
			case 1: 
					System.out.print("Create a new Survey Selected: \n");
					NewSurvey = new Survey();
					NewSurvey=NewSurvey.SetSurvey();
					break;
			case 2: 
					System.out.print("Display a Survey Selected: \n");
					NewSurvey.displaySurvey();
					break;
			case 3: 
					System.out.print("Load a Survey Selected: \n");;
				    NewSurvey = Survey.loadSurvey("SurveyList.txt","survey");
					break;
			case 4: 
					System.out.print("Save a Survey Selected: \n");
					NewSurvey.saveSurvey(NewSurvey,"survey");
					break;
			case 5: 
					System.out.print("Modify an Existing Survey Selected: \n");
					System.out.print("What survey do you wish to modify? \n");
					NewSurvey = Survey.loadSurvey("SurveyList.txt","survey");
					NewSurvey.modify(); 
					break;
			case 6: 
					System.out.print("Take a Survey Selected: \n");
					System.out.print("What Survey do you wish to take? \n");
					NewSurvey = Survey.loadSurvey("SurveyList.txt","survey");
					NewSurvey.take(T);
					break;
			case 7: 
					System.out.print("Tabulate a Survey Selected: \n");
					System.out.print("What Survey do you wish to tabulate? \n");
					NewSurvey = Survey.loadSurvey("SurveyList.txt","survey");
					NewSurvey.tabulate();
					break; 
			case 8: 
					System.out.print("Quiting: \n");
					menu();
					break;
	        default:
				    System.out.print("Error Please select a valid option \n");
				   SurveyMenu();
				   break;
	        }
		}
		
		catch(Exception e){
			System.out.print("Error mhmm select a valid option \n");
			SurveyMenu();
		}
		
		SurveyMenu();
	

	}
	/*Test Menu Prompt */
	public static void TestMenu(){
		
		System.out.println("1) Create a new Test ");
		System.out.println("2) Display a Test ");
		System.out.println("3) Load a Test ");
		System.out.println("4) Save a Test ");
		System.out.println("5) Modify an Existing Test ");
		System.out.println("6) Take a Test ");
		System.out.println("7) Tabulate a Test ");
		System.out.println("8) Grade a Test ");
		System.out.println("9) Quit ");
		
		System.out.println("Select One option from above ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			   System.out.println("int, please!");
			   sc.nextLine();
			}
		
		try{
			int i = sc.nextInt();
			switch(i) {
			case 1: 
					System.out.print("Create a new Test Selected: \n");
					 NewTest = new test();
					 NewTest=NewTest.SetTest();
					break;
			case 2: 
					System.out.print("Display a Test Selected: \n");
					NewTest.displaySurvey();
					break;
			case 3: 
					System.out.print("Load a Test Selected: \n");
					NewTest= (test) test.loadSurvey("TestList.txt","test");
					break;
			case 4: 
					System.out.print("Save a Test Selected: \n");
					NewTest.saveSurvey(NewTest, "test");
					break;
			case 5: 
				System.out.print("Modify an Existing Test Selected: \n");
				System.out.print("What survey do you wish to modify? \n");
				NewTest= (test) test.loadSurvey("TestList.txt","test");
				NewTest.modify(); 

				break;
		case 6: 
				System.out.print("Take a test Selected: \n");
				System.out.print("What test do you wish to take? \n");
				NewTest= (test) test.loadSurvey("TestList.txt","test");
				String T = "Test";
				NewTest.take(T);
 
				break; 
		case 7: 
				System.out.print("Tabulate a test Selected: \n");
				System.out.print("What test do you wish to tabulate? \n");
				NewTest= (test) test.loadSurvey("TestList.txt","test");
				NewTest.tabulate();
				break; 
		case 9: 
				System.out.print("Quiting: \n");
				menu();
				break;
		case 8: 
			System.out.print("Tabulate a test Selected: \n");
			System.out.print("What test do you wish to tabulate? \n");
			NewTest= (test) test.loadSurvey("TestList.txt","test");
			NewTest.gradeTest(NewTest);
			break;
	    default:
	    	System.out.print("Error Please select a valid option \n");
	    	TestMenu();
	        }
		}
		catch(Exception e){
			System.out.print("Error mhmm select a valid option \n");
			TestMenu();
		}
		TestMenu();
	}

}
