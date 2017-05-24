import java.util.Scanner;
import java.util.Vector;

public class MC extends Question implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Vector<String> choices = new Vector<String>(); 
	
	public MC(){
		
	}
	/*Get the Prompt from the user for the MC*/
	public void getPrompt() {
		System.out.println("Enter the prompt or your multiple choice question:");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String i = sc.nextLine();
		SetPrompt(i +"\n");
	}
	/*Sets the question for the MC and loads the vector choices*/
	public void setQuestion() {
		
		getPrompt();
		System.out.println("Enter the number of choices for your multiple choice question");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		while (!sc.hasNextInt()) {
			   System.out.println("int, please!");
			   sc.nextLine();
			}
		int n = sc.nextInt();
		
		for( int i=1; i<=n;i++){
			System.out.println("Enter choice " + "#"+i+".");
			@SuppressWarnings("resource")
			Scanner sc1 = new Scanner(System.in);  
			String c = sc1.nextLine();
			choices.add(c);

		}
		

		
	} 
	/*Sets the question*/
	public void setChoices(Vector<String> choices) {
		
		this.choices = choices;
	}
	/*returns the choices vector for the question */
	public Vector<String> getChoices(){
		
		return this.choices;
	}
	/*Display to console the question and the choices*/
	public void display(Output O){
		
		O.display(this.prompt);
		for(int i=0;i<choices.size();i++){
			O.display( (char)(i+65)+") " +choices.elementAt(i)+" ");	
			}
		O.display("\n");
	}
	
	public void Modify(){
		//check to see if it catches errors 
		System.out.println(this.prompt);
		String prompt = null;
		
		System.out.println("Do you wish to modify the prompt?");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String prompt1 = sc.nextLine();
		if(prompt1.toLowerCase().equals("yes")){
			System.out.println("Enter a new Prompt");
			@SuppressWarnings("resource")
			Scanner sc1 = new Scanner(System.in);
			prompt = sc1.nextLine();
			this.prompt = prompt;
		}
		
		System.out.println("Do you wish to modify the choice?");
		@SuppressWarnings("resource")
		Scanner sc11 = new Scanner(System.in);
		String ch = sc11.nextLine();
		if(ch.toLowerCase().equals("yes")){
			
			System.out.println("Which choice do you want to modify?");
			for(int i=0;i<choices.size();i++){
				System.out.print( (char)(i+65)+") " +choices.elementAt(i)+" ");	
				}
			System.out.print("\n");
			@SuppressWarnings("resource")
			Scanner sc2 = new Scanner(System.in);
			char c = sc2.nextLine().charAt(0);
			char ch1 = Character.toLowerCase(c);
			int pos = ch1 - 'a';
			//check if it changed 
			System.out.println(choices.elementAt(pos));
			
			@SuppressWarnings("resource")
			Scanner sc3 = new Scanner(System.in);
			String newValue = sc3.nextLine();
			choices.setElementAt(newValue, pos);
			//check if it changed 
			System.out.println(choices.elementAt(pos));

		}
	}
	

}
